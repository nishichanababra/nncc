package com.company.ecommerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;


public class SplashActivity extends ParentActivity {

    private String TAG = "SplashActivity ";
    private RelativeLayout mainRelative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setFullScreen();

        setContentView(R.layout.activity_splash);

        initObjects();

        initUIControls();

        registerForListener();

        startHandler();
    }

    @Override
    void initUIControls() {
        mainRelative = findViewById(R.id.mainRelative);
    }

    @Override
    void initObjects() {

   /*     super.initObjects();

        try {
            DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);
            databaseHelper.createDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @Override
    void setUIData() {

    }

    @Override
    void registerForListener() {
        mainRelative.setOnClickListener(this);
    }

    private void startHandler() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    /*if (PreferenceData.getBooleanPreference(SplashActivity.this, PreferenceData.isLogin)) {
                        navigateToActivity(DashboardActivity.class, true);
                    } else {*/
                    navigateToActivity(HomePageActivity.class, false);
                    //}


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mainRelative:
                navigateToActivity(LoginActivity.class, true);
                break;
        }
    }
}
