package com.css.opdpatient.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.css.opdpatient.R;
import com.css.opdpatient.utils.DateUtils;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class BranchVisitActivity extends ParentActivity {
    private String TAG = "BranchVisitActivity";

    private LinearLayout llDays, llTiming;
    private String[] allColors = {"#f08e8e", "#4dc7d9", "#4798d7", "#f1cb59", "#f1a65e", "#e383e4", "#37c4ed"};
    private TextView textViewCall, txtMobileNo;
    String imageUri = "https://maps.googleapis.com/maps/api/staticmap?zoom=15&size=350x180&maptype=roadmap&markers=color:blue|23.0340735,72.5663781";
    ImageView imageView, imgMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_visit);

        initUIControls();

        initTopBarComponents();

        bindBranchDaysLayout();

        bindBranchTimingLayout();

        setUIData();

        registerForListener();
    }//end of onCreate


    @Override
    void initUIControls() {
        super.initUIControls();
        llDays = findViewById(R.id.llDays);
        llTiming = findViewById(R.id.llTiming);
        textViewCall = findViewById(R.id.textViewCall);
        txtMobileNo = findViewById(R.id.txtMobileNo);
        imageView = findViewById(R.id.imageView);
        imgMap = findViewById(R.id.imgMap);
    }

    @Override
    void setUIData() {
        txtTopCenter.setText(getResources().getString(R.string.top_Navrngpura));
        Picasso.with(this).load("https://maps.googleapis.com/maps/api/staticmap?zoom=15&size=350x180&maptype=roadmap&markers=color:blue|23.0340735,72.5663781").into(imageView);
    }

    @Override
    protected void initTopBarComponents() {
        super.initTopBarComponents();
        setBackIcon();

    }

    /**
     * this will add the number of days
     */
    private void bindBranchDaysLayout() {
        final TextView[] txtWeekName = new TextView[7];
        View[] backgroundView = new View[7];
        for (int i = 0; i < txtWeekName.length; i++) {
            View newView = LayoutInflater.from(this).inflate(R.layout.include_visit_days, null);
            txtWeekName[i] = newView.findViewById(R.id.txtWeekName);
            backgroundView[i] = newView.findViewById(R.id.backgroundView);
            txtWeekName[i].setText(DateUtils.getAllDaysOfWeek()[i]);
            backgroundView[i].setBackgroundColor(Color.parseColor(allColors[i]));
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(27, LinearLayout.LayoutParams.MATCH_PARENT, 25);
            newView.setLayoutParams(param);
            final int finalI = i;

            newView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtWeekName[finalI].setBackgroundColor(Color.parseColor(allColors[finalI]));
                    txtWeekName[finalI].setTextColor(getResources().getColor(R.color.white));
                    for (int j = 0; j < txtWeekName.length; j++) {
                        if (j == finalI) {
                            continue;
                        }
                        txtWeekName[j].setBackgroundColor(Color.parseColor("#ffffff"));
                        txtWeekName[j].setTextColor(getResources().getColor(R.color.black));
                    }
                }
            });
            llDays.addView(newView);
        }
    }//end of bindBranchDaysLayout


    /**
     * this will add the timing of the doctor who ever will be avialable
     */
    private void bindBranchTimingLayout() {
        View view = LayoutInflater.from(this).inflate(R.layout.include_visit_timing, null);
        llTiming.addView(view);
    }//end of bindBranchTimingLayout


    @Override
    void registerForListener() {
        textViewCall.setOnClickListener(this);
        imgTopLeft.setOnClickListener(this);
        imgMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewCall:
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + txtMobileNo.getText().toString()));
                startActivity(callIntent);
                break;

            case R.id.imgTopLeft:
                onPreviousView();
                break;

            case R.id.imgMap:
                String destination = "23.040605, 72.559251" + "";
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + destination + "&mode=d");
                Log.e(TAG, "Intent uri : " + gmmIntentUri.toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mapIntent.setPackage("com.google.android.apps.maps");
                this.startActivity(mapIntent);
                break;
        }
    }
}//end of BranchVisitActivity
