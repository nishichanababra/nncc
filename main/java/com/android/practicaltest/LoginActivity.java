package com.android.practicaltest;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout tnlemail,tnlPassword;
    private Button btnLogin;
    private ImageView ivfacebook,ivgoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView(){

        tnlemail=findViewById(R.id.tnl_email);
        tnlPassword=findViewById(R.id.tnl_password);
        ivfacebook=findViewById(R.id.iv_facebook);
        ivgoogle=findViewById(R.id.iv_gmail);
        btnLogin=findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnLogin:

                Intent mIntent=new Intent(this,ImageCategoryActivity.class);
                startActivity(mIntent);
                finish();
                break;
        }
    }
}
