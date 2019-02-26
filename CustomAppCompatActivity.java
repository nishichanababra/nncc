package com.mandaliyamedicals.medical;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mandaliyamedicals.medical.generalHelper.SP;
import com.mandaliyamedicals.medical.userActivities.NotificationActivity;
import com.mandaliyamedicals.medical.userActivities.ProductCartActivity;
import com.mandaliyamedicals.medical.userActivities.ProductSearchActivity;

/**
 * Created by asama on 03-04-2017.
 */

public abstract class CustomAppCompatActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    public void showProgress(String message, boolean isCancelable) {
        dismissProgress();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(message == null ? "Please wait... " : message);
        progressDialog.setCancelable(isCancelable);
        progressDialog.show();
    }

    public void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog = null;
    }

    protected void implementToolbar(Toolbar toolbar, String appName) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(appName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    protected void implementToolbar(Toolbar toolbar, String appName, TextView txtAppName, RelativeLayout cartView, TextView txtCartCount, ImageView imgSearch) {
        setSupportActionBar(toolbar);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToSearchPage();
            }
        });
        txtAppName.setText(appName);
        setCartTotal(txtCartCount);
        cartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProductCartActivity.class));
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    protected void implementToolbar(Toolbar toolbar, RelativeLayout cartView, RelativeLayout rvNotification, TextView txtCartCount, ImageView imgSearch) {
        txtCartCount.setText(SP.getPreferences(getApplicationContext(), SP.CART_TOTAL));
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToSearchPage();
            }
        });
        setCartTotal(txtCartCount);
        cartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProductCartActivity.class));
            }
        });

        rvNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));

            }
        });

        setSupportActionBar(toolbar);
    }

    protected void setCartTotal(TextView txtCartCount) {
        txtCartCount.setText(!SP.getPreferences(getApplicationContext(), SP.CART_TOTAL).equals("") ? SP.getPreferences(getApplicationContext(), SP.CART_TOTAL) : "0");
        Animation animateCartView = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        txtCartCount.startAnimation(animateCartView);
    }

    protected void setWishTotal(TextView txtWishCount) {
        txtWishCount.setText(!SP.getPreferences(getApplicationContext(), SP.WISH_TOTAL).equals("") ? SP.getPreferences(getApplicationContext(), SP.WISH_TOTAL) : "0");
        Animation animateCartView = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        txtWishCount.startAnimation(animateCartView);
    }

    protected void setNotificationTotal(TextView txtCartCount) {
        String notificationCount = !SP.getPreferences(getApplicationContext(), SP.TOTAL_NOTIFICATION).equals("") ? SP.getPreferences(getApplicationContext(), SP.TOTAL_NOTIFICATION) : "0";
        txtCartCount.setText(notificationCount);
        Animation animateCartView = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        txtCartCount.startAnimation(animateCartView);
    }

    public abstract void onResumeCalled();

    @Override
    protected void onResume() {
        super.onResume();
        onResumeCalled();
    }

    private void navigateToSearchPage() {
        startActivity(new Intent(this, ProductSearchActivity.class).putExtra("RefillMedicine", "").putExtra("otc", ""));
    }
}