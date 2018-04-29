package com.company.ecommerce;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SearchProductActivity extends ParentActivity {

    private String TAG = "SearchProductActivity ";
    private TextView txtModarts, txtLampshades, txtPainting, txtFrame, txtShowpiece, txtDesigning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);


        initUIControls();

        initTopBarComponents();

        registerForListener();

        setUIData();
    }

    @Override
    void initUIControls() {
        imgTopLeft = findViewById(R.id.imgTopLeft);
        txtModarts = findViewById(R.id.txtModarts);
        txtLampshades = findViewById(R.id.txtLampshades);
        txtPainting = findViewById(R.id.txtPainting);
        txtFrame = findViewById(R.id.txtFrame);
        txtShowpiece = findViewById(R.id.txtShowpiece);
        txtDesigning = findViewById(R.id.txtDesigning);

    }

    @Override
    void registerForListener() {
        imgTopLeft.setOnClickListener(this);
        txtModarts.setOnClickListener(this);
        txtLampshades.setOnClickListener(this);
        txtPainting.setOnClickListener(this);
        txtFrame.setOnClickListener(this);
        txtShowpiece.setOnClickListener(this);
        txtDesigning.setOnClickListener(this);


    }

    @Override
    protected void initTopBarComponents() {
        super.initTopBarComponents();
        imgTopLeft.setImageDrawable(getResources().getDrawable(R.drawable.top_back));
    }

    @Override
    void setUIData() {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.imgTopLeft:
                onPreviousView();
                break;

            case R.id.txtModarts:
                navigateToActivity(ModArtsActivity.class, false);
                break;

            case R.id.txtLampshades:
                navigateToActivity(LampShadeActivity.class, false);
                break;
            case R.id.txtPainting:
                navigateToActivity(PaintingActivity.class, false);
                break;
            case R.id.txtFrame:
                navigateToActivity(FrameActivity.class, false);
                break;
            case R.id.txtShowpiece:
                navigateToActivity(ShowPieceActivity.class, false);
                break;

            case R.id.txtDesigning:
                navigateToActivity(DesigningActivity.class, false);
                break;
        }
    }
}

