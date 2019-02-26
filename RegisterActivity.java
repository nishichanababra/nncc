package com.mandaliyamedicals.medical.userActivities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mandaliyamedicals.medical.CustomAppCompatActivity;
import com.mandaliyamedicals.medical.Dashboard;
import com.mandaliyamedicals.medical.R;
import com.mandaliyamedicals.medical.databinding.ActivityRegisterBinding;
import com.mandaliyamedicals.medical.generalHelper.AppUtil;
import com.mandaliyamedicals.medical.generalHelper.DialogUtil;
import com.mandaliyamedicals.medical.generalHelper.GH;
import com.mandaliyamedicals.medical.generalHelper.L;
import com.mandaliyamedicals.medical.generalHelper.SP;
import com.mandaliyamedicals.medical.generalHelper.sms_broadcast_receiver_helper.OnSmsCatchListener;
import com.mandaliyamedicals.medical.generalHelper.sms_broadcast_receiver_helper.SmsVerifyCatcher;
import com.mandaliyamedicals.medical.models.LoginResultInfo;
import com.mandaliyamedicals.medical.retrofit.RetrofitBuilder;
import com.mandaliyamedicals.medical.retrofit.RetrofitCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by asama on 06-04-2017.
 */

public class RegisterActivity extends CustomAppCompatActivity implements View.OnClickListener {
    private Context mContext;
    private ActivityRegisterBinding binding;

    private Dialog progressDialog;
    private SmsVerifyCatcher smsVerifyCatcher;
    private Dialog dialogOTP;
    private TextView error;
    private EditText etOtp;
    private boolean isMobileVerified = false;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mVerificationId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        implementToolbar(binding.includedToolbar.mToolBar, getString(R.string.register));

        binding.btnRegister.setOnClickListener(this);
        binding.urlTnC.setOnClickListener(this);
        binding.urlTnC.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        GH.addEditTextChangeListener(binding.tilFirstname, binding.etFirstname);
        GH.addEditTextChangeListener(binding.tilMobileNumber, binding.etMobileNumber);
        GH.addEditTextChangeListener(binding.tilEmail, binding.etEmail);
        GH.addEditTextChangeListener(binding.tilPassword, binding.etPassword);


    }


    private void setSMSBroadcast() {
        //init SmsVerifyCatcher
        smsVerifyCatcher = new SmsVerifyCatcher(this, new OnSmsCatchListener<String>() {
            @Override
            public void onSmsCatch(String message) {
                Toast.makeText(mContext, "OTP Received:" + message, Toast.LENGTH_LONG).show();
                String code = GH.parseCode(message);//Parse verification code
                L.showError("OTP Received after parsed = " + code);
                if (dialogOTP != null && dialogOTP.isShowing()) {
                    dialogOTP.cancel();
                }

            }
        });
    }

    private void firbaseTest() {
        showProgress(getString(R.string.requesting), false);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + binding.etMobileNumber.getText().toString().trim(),         // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verificaiton without
            //     user action.
            dismissProgress();
            Toast.makeText(mContext, "onVerificationCompleted " + phoneAuthCredential.toString(), Toast.LENGTH_SHORT).show();
            signInWithPhoneAuthCredential(phoneAuthCredential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            showProgress(getString(R.string.requesting), false);
            Toast.makeText(mContext, "onVerificationFailed " + e.toString(), Toast.LENGTH_SHORT).show();

            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                dismissProgress();
                Toast.makeText(mContext, "Invalid Request " + e.toString(), Toast.LENGTH_SHORT).show();
            } else if (e instanceof FirebaseTooManyRequestsException) {
                dismissProgress();
                Toast.makeText(mContext, "The SMS quota for the project has been exceeded " + e.toString(), Toast.LENGTH_SHORT).show();
            }
            // Show a message and update the UI
            // ...
        }

        @Override
        public void onCodeSent(String verificationId,
                               PhoneAuthProvider.ForceResendingToken token) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.

            mVerificationId = verificationId;
            mResendToken = token;
            /*Toast.makeText(mContext, "onCodeSent " + verificationId, Toast.LENGTH_SHORT).show();*/
            dismissProgress();
            showDialogForOTP();
            // Save verification ID and resending token so we can use them later

            PhoneAuthProvider.ForceResendingToken mResendToken = token;

            // ...
        }

    };

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (dialogOTP != null && dialogOTP.isShowing()) {
                                dialogOTP.cancel();
                            }

                            requestToRegisterUser();


                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(mContext, "Verify code invalid ", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });
    }

    private void showDialogForOTP() {
        dialogOTP = new Dialog(RegisterActivity.this);
        dialogOTP.setCancelable(false);
        dialogOTP.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogOTP.setCancelable(false);
        try {
            dialogOTP.setContentView(R.layout.view_otp_dialog);
        } catch (Exception e) {
            L.showError(e.getMessage());
        }
        LinearLayout llResend = dialogOTP.findViewById(R.id.llResend);
        llResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogOTP.cancel();
                firbaseTest();
            }
        });
        dialogOTP.setCanceledOnTouchOutside(true);
        Button btn = dialogOTP.findViewById(R.id.btnSend);
        etOtp = dialogOTP.findViewById(R.id.etOtp);
        Button txtCanel = dialogOTP.findViewById(R.id.btnCancle);
        TextView txtMobile = dialogOTP.findViewById(R.id.txtMobile);
        txtMobile.setText(binding.etMobileNumber.getText().toString().trim());
        txtCanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogOTP.cancel();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, etOtp.getText().toString().trim());
                signInWithPhoneAuthCredential(credential);

            }
        });
        dialogOTP.show();
    }

    private void showSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                if (isValidDataToRegister()) {
                    if (AppUtil.isConnected(mContext)) {
                        /*requestToRegisterUser();*/
                        requestToCheckMobileEmailExist();
                    }
                }
                break;

            case R.id.urlTnC:
                startActivity(new Intent(mContext, WebViewActivity.class).putExtra("url", "http://Mandaliya Medicals.com/Content/terms_condition").putExtra("link", getString(R.string.term_conditions)));
                break;

            default:
                break;
        }
    }

    private void requestToCheckMobileEmailExist() {
        showProgress(getString(R.string.requesting), true);
        RequestBody userEmail = RequestBody.create(MediaType.parse("text/plain"), binding.etEmail.getText().toString().trim());
        RequestBody userMobile = RequestBody.create(MediaType.parse("text/plain"), binding.etMobileNumber.getText().toString().trim());


        Call<ResponseBody> bodyCall = RetrofitBuilder.getInstance().getRetrofit(mContext).checkEmailMobile(userEmail, userMobile);
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dismissProgress();
                try {
                    JSONObject jsonObject = new JSONObject(GH.RetrofitBufferReaderResponse(response));

                    if (jsonObject.has("status")) {

                        if (jsonObject.getString("status").equalsIgnoreCase("success")) {
                            firbaseTest();
                        } else {
                            L.showToast(mContext, jsonObject.getString("msg"));
                        }
                    } else {
                        L.showToast(mContext, jsonObject.getString("msg"));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    L.showToast(mContext, getString(R.string.str_something_went_wrong_please_try_again));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dismissProgress();
                L.showToast(mContext, getString(R.string.str_something_went_wrong_please_try_again));
            }
        });
    }


    private void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }


    private void requestToRegisterUser() {
        RequestBody fisrtName = RequestBody.create(MediaType.parse("text/plain"), binding.etFirstname.getText().toString().trim());
        RequestBody code = RequestBody.create(MediaType.parse("text/plain"), "+91");
        RequestBody mobile = RequestBody.create(MediaType.parse("text/plain"), binding.etMobileNumber.getText().toString().trim());
        RequestBody emailId = RequestBody.create(MediaType.parse("text/plain"), binding.etEmail.getText().toString().trim());
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), binding.etPassword.getText().toString().trim());
        RequestBody deviceId = RequestBody.create(MediaType.parse("text/plain"), FirebaseInstanceId.getInstance().getToken() == null ? "Empty" : FirebaseInstanceId.getInstance().getToken());
        RequestBody deviceToken = RequestBody.create(MediaType.parse("text/plain"), "");
        RequestBody deviceType = RequestBody.create(MediaType.parse("text/plain"), "android");


        showProgress(getString(R.string.requesting), true);
        Call<ArrayList<LoginResultInfo>> arrayListCall = RetrofitBuilder.getInstance().getRetrofit(mContext)
                .userRegister(fisrtName,
                        mobile,
                        emailId,
                        code,
                        password,
                        deviceId,
                        deviceToken,
                        deviceType
                );

        arrayListCall.enqueue(new RetrofitCallback<ArrayList<LoginResultInfo>>(mContext) {
            @Override
            public void onSuccess(ArrayList<LoginResultInfo> models) {
                dismissProgress();
                SP.savePreferences(mContext, SP.LOGIN_STATUS, SP.SP_TRUE);
                SP.savePreferences(mContext, SP.USER_FIRST_NAME, models.get(0).getFirstname());
                //  SP.savePreferences(mContext, SP.USER_LAST_NAME, models.get(0).getLastname());
                SP.savePreferences(mContext, SP.USER_MOBILE, models.get(0).getMobilenumber());
                SP.savePreferences(mContext, SP.USER_EMAIL, models.get(0).getEmail());
                SP.savePreferences(mContext, SP.USER_PROFILE, models.get(0).getUserprofile());
                SP.savePreferences(mContext, SP.USER_DOB, models.get(0).getDob());
                SP.savePreferences(mContext, SP.USER_GENDER, models.get(0).getGender());
                SP.savePreferences(mContext, SP.USER_LAT, models.get(0).getLatitude());
                SP.savePreferences(mContext, SP.USER_LONG, models.get(0).getLongitude());
                SP.savePreferences(mContext, SP.USER_ID, models.get(0).getUserId());
                SP.savePreferences(mContext, SP.CALL_US, models.get(0).getCall_us());
                SP.savePreferences(mContext, SP.USER_AGE, models.get(0).getAge());
                SP.savePreferences(mContext, SP.COUNTRY_CODE, models.get(0).getCountry_code());
                SP.savePreferences(mContext, SP.USER_PROFESSIONAl, models.get(0).getProfessional());
                SP.savePreferences(mContext, SP.USER_DELIVERY_ADDRESS_ID, models.get(0).getAddress_id());
                SP.savePreferences(mContext, SP.USER_BILLING_ADDRESS_ID, models.get(0).getAddress_id());
                Intent intent = new Intent(mContext, Dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }

            @Override
            public void onFail(String message) {
                dismissProgress();
                DialogUtil.showMessageDialog(mContext, message);

            }
        });
    }


    private boolean isValidDataToRegister() {
        if (!GH.isValidString(binding.etFirstname.getText().toString().trim())) {
            GH.setEditTextError(getString(R.string.enter_firstname), binding.tilFirstname);
            return false;
        } else if (!GH.isValidMobileNumber(binding.etMobileNumber.getText().toString().trim())) {
            GH.setEditTextError(getString(R.string.enter_valid_mobile_number), binding.tilMobileNumber);
            return false;
        } else if (!GH.isValidEmail(binding.etEmail.getText().toString().trim())) {
            GH.setEditTextError(getString(R.string.enter_valid_email), binding.tilEmail);
            return false;
        }  else if (!binding.cbTermsAndConditions.isChecked()) {
            DialogUtil.showMessageDialog(mContext, getString(R.string.please_agree_to_the_company_terms_of_use));
            return false;
        } else {
            return true;
        }
    }


    private boolean isValidString(String trim) {
        if (trim.equalsIgnoreCase("") || trim.length() <= 2)
            return false;
        else
            return true;
    }

    @Override
    public void onResumeCalled() {
    }
}
