package app.citta.utilities.GeneralizedModules.MainActivity.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.citta.utilities.GeneralizedModules.FingerPrint.Activity.FingerPrintActivity;
import app.citta.utilities.GeneralizedModules.Firebase.Activity.FirebasePushNotification;
import app.citta.utilities.GeneralizedModules.FloationActionButton.Floationg_Action_Button_MainActivity;
import app.citta.utilities.GeneralizedModules.GoogleMap.Activity.ShowLocationOnGoogleMap;
import app.citta.utilities.GeneralizedModules.ImageOperations.Activity.DownloadImage;
import app.citta.utilities.GeneralizedModules.ImageOperations.Activity.ShrinkAndScaleImages;
import app.citta.utilities.GeneralizedModules.MainActivity.Adapter.MainActivityAdapter;
import app.citta.utilities.GeneralizedModules.Barcode.Activity.BarcodeScanner;
import app.citta.utilities.GeneralizedModules.ImageOperations.Activity.ImageUpload;
import app.citta.utilities.GeneralizedModules.Localizations.Activity.LocalizationForLanguages;
import app.citta.utilities.GeneralizedModules.NavigationDrawer.Activity.NavigationDrawer;
import app.citta.utilities.GeneralizedModules.SendOTP.Activity.SendOTP;
import app.citta.utilities.GeneralizedModules.MainActivity.Listener.MainActivityItemListeners;
import app.citta.utilities.GeneralizedModules.MainActivity.POJO.mainActivityPOJO;
import app.citta.utilities.GeneralizedModules.UpdateLocation.Activity.UpdateLocationViaBackgroundService;
import app.citta.utilities.R;
import app.citta.utilities.utilities.Config;

public class MainActivity extends AppCompatActivity implements MainActivityItemListeners {

    RecyclerView recyclerViewMain;
    List<mainActivityPOJO> mainActivityPOJOs;
    MainActivityAdapter mainActivityAdapter;
    Toolbar toolbar;
    private Boolean exit = false;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewMain = (RecyclerView) findViewById(R.id.recyclerMain);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerViewMain.setLayoutManager(mLayoutManager);
        recyclerViewMain.setItemAnimator(new DefaultItemAnimator());
        inittoolbar();

        if (Config.getInstance().isInternetAvailable(MainActivity.this)) {
            mainActivityPOJOs = new ArrayList<>();

            mainActivityPOJO mainActivityPOJO = new mainActivityPOJO(getString(R.string.imageoperation), 1);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.alarmmanager), 2);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.shrinkimage), 3);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.localization), 4);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.barcodescanner), 5);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.navigationdrawer), 6);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.sendotp), 7);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.autodetectotp), 8);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.downloadimage), 9);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.firebasepushnotification), 10);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.firebaseremoteconfig), 11);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.updatelocationfrombackgroundservice), 12);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.googlemap), 13);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.sharedpreference), 14);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.encryptdecrypt), 15);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.materialdesign), 16);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.facebooksignup), 17);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.googlesignup), 18);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.fingerprint), 19);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityPOJO = new mainActivityPOJO(getString(R.string.FloationActionButtonAnimation), 20);
            mainActivityPOJOs.add(mainActivityPOJO);

            mainActivityAdapter = new MainActivityAdapter(MainActivity.this, mainActivityPOJOs);
            recyclerViewMain.setAdapter(mainActivityAdapter);
            mainActivityAdapter.registerMainActivityItemClick(MainActivity.this);

        } else {
            Config.getInstance().GlobalInternetDialog(MainActivity.this);
        }
    }

    public void inittoolbar() {
        // Find the toolbar view inside the activity layout
        toolbar = (Toolbar) findViewById(R.id.simple_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView mTitle = (TextView) toolbar.findViewById(R.id.simple_toolbar_title);
        ImageView back = (ImageView) toolbar.findViewById(R.id.app_icon);
        mTitle.setText(getResources().getString(R.string.androidGeneric));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Uri uri = Uri.parse("http://cittasolutions.com/");       // missing 'http://' will cause crashed
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void mainActivityItemClickListner(String itemName, int position) {
        Intent intent = null;
        Bundle extras = null;
        switch (position) {
            case 1:
                intent = new Intent(MainActivity.this,ImageUpload.class);
                startActivity(intent);
                break;

            case 2:
                Config.getInstance().ToastWithMsg(MainActivity.this, itemName + String.valueOf(position));
                break;

            case 3:
                intent = new Intent(MainActivity.this,ShrinkAndScaleImages.class);
                startActivity(intent);
                break;

            case 4:
                intent = new Intent(MainActivity.this,LocalizationForLanguages.class);
                startActivity(intent);
                finish();
                break;

            case 5:
                intent = new Intent(MainActivity.this,BarcodeScanner.class);
                startActivity(intent);
                break;

            case 6:
                intent = new Intent(MainActivity.this,NavigationDrawer.class);
                startActivity(intent);
                break;

            case 7:
                intent = new Intent(MainActivity.this,SendOTP.class);
                extras = new Bundle();
                extras.putInt("from", 7);
                intent.putExtras(extras);
                startActivity(intent);
                break;

            case 8:
                intent = new Intent(MainActivity.this,SendOTP.class);
                extras = new Bundle();
                extras.putInt("from", 8);
                intent.putExtras(extras);
                startActivity(intent);
                break;

            case 9:
                intent = new Intent(MainActivity.this,DownloadImage.class);
                startActivity(intent);
                break;

            case 10:
                intent = new Intent(MainActivity.this,FirebasePushNotification.class);
                startActivity(intent);
                break;

            case 11:
                Config.getInstance().ToastWithMsg(MainActivity.this, itemName + String.valueOf(position));
                break;

            case 12:
                intent = new Intent(MainActivity.this,UpdateLocationViaBackgroundService.class);
                startActivity(intent);
                break;

            case 13:
                intent = new Intent(MainActivity.this,ShowLocationOnGoogleMap.class);
                startActivity(intent);
                break;

            case 14:
                Config.getInstance().ToastWithMsg(MainActivity.this, itemName + String.valueOf(position));
                break;

            case 15:
                Config.getInstance().ToastWithMsg(MainActivity.this, itemName + String.valueOf(position));
                break;

            case 16:
                Config.getInstance().ToastWithMsg(MainActivity.this, itemName + String.valueOf(position));
                break;

            case 17:
                Config.getInstance().ToastWithMsg(MainActivity.this, itemName + String.valueOf(position));
                break;

            case 18:
                Config.getInstance().ToastWithMsg(MainActivity.this, itemName + String.valueOf(position));
                break;

            case 19:
                intent = new Intent(MainActivity.this, FingerPrintActivity.class);
                startActivity(intent);
                break;

            case 20:
                intent = new Intent(MainActivity.this, Floationg_Action_Button_MainActivity.class);
                startActivity(intent);
                break;

            default:
                Config.getInstance().ToastWithMsg(MainActivity.this,getString(R.string.demoappMessage));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, getResources().getString(R.string.pressagaintoexit), Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }
}
