package com.company.ecommerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;

import com.company.adapter.DataProvider;
import com.company.adapter.RecyclerAdapter;


public class DesigningActivity extends ParentActivity {

    private final String TAG = "DesigningActivity";
    private DesigningActivity.MyViewPagerAdapter myViewPagerAdapter;

    private RecyclerView recyclerView;
    private TextView txtTopCenter;
    private ImageView imgTopLeft;
    private ImageView imgsearch;


    private String product_names[] = {"Mad Arts1", "Mad Arts2", "Mad Arts3", "Mad Arts4"};
    private int img_recycler[] = {R.drawable.night_lamp, R.drawable.stand_lamp, R.drawable.lamp, R.drawable.round_lamp};
    private String price[] = {" 500", " 650", " 700", " 350"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designing);

        initUIControls();

        initTopBarComponents();

        registerForListener();

        setUIData();
        prepareData();

    }

    @Override
    void initUIControls() {
        txtTopCenter = findViewById(R.id.txtTopCenter);
        imgTopLeft = findViewById(R.id.imgTopLeft);
        imgsearch = findViewById(R.id.imgsearch);
    }

    @Override
    void setUIData() {
        txtTopCenter.setText(getResources().getString(R.string.top_designing));
    }

    @Override
    void registerForListener() {
        imgTopLeft.setOnClickListener(this);
        imgsearch.setOnClickListener(this);
    }

    @Override
    protected void initTopBarComponents() {
        txtTopCenter.setVisibility(View.VISIBLE);
    }

    private void prepareData() {

        ArrayList<DataProvider> arrayList = new ArrayList<>();
        for (int i = 0; i < product_names.length; i++) {
            DataProvider dataProvider = new DataProvider();
            dataProvider.setImgrecycler(img_recycler[i]);
            dataProvider.setName(product_names[i]);
            dataProvider.setPrice(price[i]);

            arrayList.add(dataProvider);
        }


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        Log.d(TAG, "DesigningActivity: prepareData");
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        Log.d(TAG, "DesigningActivity: " + arrayList.size());
        RecyclerAdapter adapter = new RecyclerAdapter(getApplicationContext(), arrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, GridLayoutManager.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, GridLayoutManager.VERTICAL));

    }

  /*  private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }//end of getItem*/


    private class MyViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {

        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgTopLeft:

                navigateToActivity(HomePageActivity.class, false);
                break;
            case R.id.imgsearch:
                navigateToActivity(SearchProductActivity.class, false);
                break;
        }
    }
}