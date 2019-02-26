package com.mandaliyamedicals.medical;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mandaliyamedicals.medical.generalHelper.AppUtil;
import com.mandaliyamedicals.medical.generalHelper.SP;
import com.mandaliyamedicals.medical.userActivities.AddRefillMedicineActivity;
import com.mandaliyamedicals.medical.userActivities.ProductCartActivity;
import com.mandaliyamedicals.medical.userActivities.ProductSearchActivity;

/**
 * Created by asama on 07-04-2017.
 */

public abstract class CustomRecyclerViewActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    public void showProgress(String message, boolean isCancelable) {
        dismissProgress();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(message == null ? "Please wait... " : message);
        progressDialog.setCancelable(isCancelable);
        progressDialog.show();
    }


    protected void addRefill(Toolbar toolbar, String appName, TextView txtAppName, RelativeLayout imgAdd) {
        setSupportActionBar(toolbar);
        txtAppName.setText(appName);
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), AddRefillMedicineActivity.class).putExtra("RefillMedicine", "RefillMedicine"));
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

    protected boolean isConnected(RecyclerView recyclerView, LinearLayout linearLayout, Button btnError, TextView textView) {
        if (AppUtil.isConnected(this)) {
            recyclerView.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
            return true;
        } else {
            recyclerView.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
            textView.setText(getString(R.string.no_internet_connection_found));
            btnError.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onErrorButtonClicked();
                }
            });
            return false;
        }
    }

    public void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog = null;
    }

    protected void implementToolbar(Toolbar toolbar, String appName, TextView txtAppName, RelativeLayout cartView, TextView txtCartCount, ImageView imgSearch) {
        setSupportActionBar(toolbar);
        txtAppName.setText(appName);
        setCartTotal(txtCartCount);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ProductSearchActivity.class).putExtra("RefillMedicine", "").putExtra("otc", ""));
            }
        });
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

    protected void setCartTotal(TextView txtCartCount) {
        txtCartCount.setText(SP.getPreferences(getApplicationContext(), SP.CART_TOTAL));
        Animation animateCartView = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        txtCartCount.startAnimation(animateCartView);
    }

    public void dataSuccess(RecyclerView recyclerView, LinearLayout linearLayout) {
        recyclerView.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
    }


    public void dataError(RecyclerView recyclerView, LinearLayout linearLayout, Button btnError, TextView textView, String error) {
        recyclerView.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
        textView.setText(error);
        btnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorButtonClicked();
            }
        });
    }

    public abstract void onErrorButtonClicked();

    public abstract void onResumeCalled();

    @Override
    protected void onResume() {
        super.onResume();
        onResumeCalled();
    }
}