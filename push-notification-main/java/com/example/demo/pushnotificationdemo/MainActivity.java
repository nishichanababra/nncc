package com.example.demo.pushnotificationdemo;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        if (!TextUtils.isEmpty(refreshedToken)) {

            Toast.makeText(this, refreshedToken, Toast.LENGTH_SHORT).show();
            Log.d("tokentoken", refreshedToken);
        }

    }
}
