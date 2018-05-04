package app.citta.utilities.GeneralizedModules.SendOTP.Activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import app.citta.utilities.utilities.Config;
import butterknife.BindView;
import butterknife.ButterKnife;
import app.citta.utilities.R;
import app.citta.utilities.Validator.Validator;

/**
 * Created by ws-16 on 7/17/2017.
 */

public class SendOTP extends AppCompatActivity {

    @BindView(R.id.tiedt_mobile)TextInputEditText tidt_mobile;
    @BindView(R.id.btn_sendOTP)Button btn_sendOTp;

    String authKey, senderID, route,  message , mobilenumber;
    int activityfrom;
    public static String randomOtp;
    Long otp;

    Toolbar toolbar;

    public static EditText edt_otp;
    ImageView iv_otpStatus;
    Button btn_verifyotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ButterKnife.bind(this);
        inittoolbar();

        PackageManager pm = SendOTP.this.getPackageManager();
        ComponentName componentName = new ComponentName(SendOTP.this, AutoDetectOTP.class);
        pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        activityfrom = extras.getInt("from");

        otp = Config.getInstance().createRandomInteger(100000,999999L);
        randomOtp = otp.toString();

        btn_sendOTp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Config.getInstance().isInternetAvailable(SendOTP.this)){

                    if(validation()){
                        Config.getInstance().startProgressdialog(SendOTP.this,Config.PLEASE_WAIT);
                        Thread thread = new Thread(){
                            @Override
                            public void run() {
                                super.run();
                                sendOtp();
                            }
                        };thread.start();
                    }
                }else{
                    Config.getInstance().GlobalInternetDialog(SendOTP.this);
                }
            }
        });
    }

    void inittoolbar() {
        // Find the toolbar view inside the activity layout
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        ImageView back = (ImageView) toolbar.findViewById(R.id.back);
        mTitle.setText(getResources().getString(R.string.scanQr));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public boolean validation(){
        return Validator.getInstance().validateMobile(tidt_mobile,SendOTP.this);
    }

    /* Function for send OTP to entered mobile number */
    public void sendOtp() {

        mobilenumber = tidt_mobile.getText().toString().trim();

        authKey = Config.OTP_authKey;
        senderID = Config.OTP_senderID;
        message =  "Your unique one-time password (OTP) is "+ String.valueOf(randomOtp);
        route = Config.OTP_route;
        URLConnection myURLConnection = null;
        URL myURL = null;
        BufferedReader reader = null;
        String encoded_message = URLEncoder.encode(message);
        String mainUrl = "https://control.msg91.com/api/sendhttp.php?";
        StringBuilder sbPostData = new StringBuilder(mainUrl);
        sbPostData.append("authkey=" + authKey);
        sbPostData.append("&mobiles=" + mobilenumber);
        sbPostData.append("&message=" + encoded_message);
        sbPostData.append("&route=" + route);
        sbPostData.append("&sender=" + senderID);
        mainUrl = sbPostData.toString();
        try {
            //prepare connection
            myURL = new URL(mainUrl);
            myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            //reading response
            String response;
            while ((response = reader.readLine()) != null)
                //print response
                Log.d("RESPONSE", "" + response);
            //finally close connection
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Config.getInstance().stopProgressdialog();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.activity_otp_varify);
                initNewComponent();
            }
        });
    }

    public void initNewComponent(){

        iv_otpStatus = (ImageView)findViewById(R.id.iv_status);
        btn_verifyotp = (Button)findViewById(R.id.btn_verifyOTP);
        edt_otp = (EditText) findViewById(R.id.edt_OTP);
        inittoolbar();

        iv_otpStatus.setImageResource(R.mipmap.accessnotdefined);
        btn_verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEntered = edt_otp.getText().toString().trim();
                if(userEntered != null && !userEntered.equals("") && edt_otp.length() != 0) {
                    if (userEntered.equals(randomOtp)) {
                        iv_otpStatus.setImageResource(R.mipmap.accesson);
                    } else {
                        iv_otpStatus.setImageResource(R.mipmap.accessoff);
                    }
                }else{
                    edt_otp.setError(getString(R.string.pleaseenterotphere));
                    edt_otp.requestFocus();
                }
            }
        });

        if(activityfrom == 8){

            PackageManager pm = SendOTP.this.getPackageManager();
            ComponentName componentName = new ComponentName(SendOTP.this, AutoDetectOTP.class);
            pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

            btn_verifyotp.setVisibility(View.GONE);

            edt_otp.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    String currentOTP = edt_otp.getText().toString().trim();

                    if(currentOTP.length() == 6 && currentOTP.equals(randomOtp)){

                        iv_otpStatus.setImageResource(R.mipmap.accesson);

                        Config.getInstance().GlobalAlertDialog(SendOTP.this,getResources().getString(R.string.autootpdetected),getResources().getString(R.string.otpAutodetected),false);

                        PackageManager pm = SendOTP.this.getPackageManager();
                        ComponentName componentName = new ComponentName(SendOTP.this, AutoDetectOTP.class);
                        pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

                    }else if(currentOTP.length() == 6 && !currentOTP.equals(randomOtp)){
                        iv_otpStatus.setImageResource(R.mipmap.accessoff);
                    }else{
                        iv_otpStatus.setImageResource(R.mipmap.accessnotdefined);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    /*   auto detect otp reciever  STATIC class     */
    public static class AutoDetectOTP extends BroadcastReceiver {
        // Get the object of SmsManager
        final SmsManager sms = SmsManager.getDefault();

        public void onReceive(Context context, Intent intent) {
            // Retrieves a map of extended data from the intent.
            final Bundle bundle = intent.getExtras();
            try {
                if (bundle != null) {
                    final Object[] pdusObj = (Object[]) bundle.get("pdus");

                    for (int i = 0; i < pdusObj.length; i++) {
                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        String senderNum = currentMessage.getDisplayOriginatingAddress();
                        String message = currentMessage.getDisplayMessageBody();
                        if(senderNum.contains(Config.OTP_senderID) && message.contains(randomOtp)){
                            edt_otp.setText(randomOtp);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
