package com.company.ecommerce;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProductDescriptionActivity extends ParentActivity {

    private final String TAG = "ProductDescriptionActivity";
    private TextView txtWishlist;
    private Button btnAddtocart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);


        initUIControls();

        setUIData();

        registerForListener();

    }

    @Override
    void setUIData() {
        //txtTopCenter.setText(getResources().getString(R.string.top_circlelamp));
    }

    @Override
    void initUIControls() {
        txtWishlist = findViewById(R.id.txtWishlist);
        imgTopLeft = findViewById(R.id.imgTopLeft);
        btnAddtocart = findViewById(R.id.btnAddtocart);
    }

    @Override
    void registerForListener() {
        txtWishlist.setOnClickListener(this);
        imgTopLeft.setOnClickListener(this);
        btnAddtocart.setOnClickListener(this);
    }

    @Override
    protected void initTopBarComponents() {
        super.initTopBarComponents();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtWishlist:
                navigateToActivity(LoginActivity.class, false);
                break;

            case R.id.imgTopLeft:
                onBackPressed();

            case R.id.btnAddtocart:
                navigateToActivity(LoginActivity.class, false);
        }
    }
}
