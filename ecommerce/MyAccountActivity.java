package com.company.ecommerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAccountActivity extends ParentActivity {

    private String TAG = "MyAccountActivity";
    private TextView txtPersonalInformation, txtChangePassword, txtLogout;
    ImageView imgsearch, img_addtocart;
    private View fragment;
    private EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        initUIControls();
        initTopBarComponents();
        registerForListener();
        setUIData();

    }

    @Override
    void initUIControls() {
        txtPersonalInformation = findViewById(R.id.txtPersonalInformation);
        txtChangePassword = findViewById(R.id.txtChangePassword);
        txtLogout = findViewById(R.id.txtLogout);
        imgTopLeft = findViewById(R.id.imgTopLeft);

    }

    @Override
    protected void initTopBarComponents() {
        super.initTopBarComponents();

        edtSearch.setVisibility(View.INVISIBLE);
        imgsearch.setVisibility(View.INVISIBLE);
        img_addtocart.setVisibility(View.INVISIBLE);

    }

    @Override
    void registerForListener() {
        super.registerForListener();
        txtPersonalInformation.setOnClickListener(this);
        txtChangePassword.setOnClickListener(this);
        txtLogout.setOnClickListener(this);

        imgTopLeft.setOnClickListener(this);
    }

    @Override
    void setUIData() {
        super.setUIData();
        edtSearch.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
           /* case R.id.txtPersonalInformation:
                navigateToActivity(PersonalInformationActivity.class, false);
                break;

            case R.id.txtChangePassword:
                navigateToActivity(ChangePasswordActivity.class, false);
                break;*/

            case R.id.txtLogout:
                navigateToActivity(LoginActivity.class, false);
                break;

            case R.id.imgTopLeft:
                navigateToActivity(HomePageActivity.class, false);
                break;

        }
    }
}
