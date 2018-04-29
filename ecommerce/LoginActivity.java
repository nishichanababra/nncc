package com.company.ecommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends ParentActivity {

    private final String TAG = "LoginActivity";
    private Button btnLogin;
    private TextView txtRegisterNow;
    private EditText edtPatientUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUIControls();

        registerForListener();
    }

    @Override
    void initUIControls() {
        btnLogin = findViewById(R.id.btnLogin);
        txtRegisterNow = findViewById(R.id.txtRegisterNow);

    }

    @Override
    void registerForListener() {
        btnLogin.setOnClickListener(this);
        txtRegisterNow.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /*case R.id.btnLogin:

             *//*   if (edtPatientUsername.getText().toString().isEmpty()) {
                    edtPatientUsername.setError("Mobile no required");
                } else {*//*
                    navigateToActivity(RegistrationActivity.class, false);
                break;*/

            case R.id.txtRegisterNow:
                navigateToActivity(RegistrationActivity.class, false);
                break;
        }
    }
}
