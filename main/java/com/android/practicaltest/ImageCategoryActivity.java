package com.android.practicaltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ImageCategoryActivity extends AppCompatActivity implements DashboardFragmentAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private DashboardFragmentAdapter dashboardFragmentAdapter;
    private ArrayList<NavItemModel> navItemModels=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_category);

        recyclerView=findViewById(R.id.card_recycler_view);

        navItemModels.add(new NavItemModel("Store Name","Category Name",R.drawable.home_thumbnail,R.drawable.like_filled,R.drawable.chat,"10","10"));
        navItemModels.add(new NavItemModel("Store Name","Category Name",R.drawable.home_thumbnail,R.drawable.like,R.drawable.chat,"10","10"));
        navItemModels.add(new NavItemModel("Store Name","Category Name",R.drawable.home_thumbnail,R.drawable.like_filled,R.drawable.chat,"10","10"));
        navItemModels.add(new NavItemModel("Store Name","Category Name",R.drawable.home_thumbnail,R.drawable.like,R.drawable.chat,"10","10"));
        navItemModels.add(new NavItemModel("Store Name","Category Name",R.drawable.home_thumbnail,R.drawable.like_filled,R.drawable.chat,"10","10"));
        navItemModels.add(new NavItemModel("Store Name","Category Name",R.drawable.home_thumbnail,R.drawable.like,R.drawable.chat,"10","10"));
        navItemModels.add(new NavItemModel("Store Name","Category Name",R.drawable.home_thumbnail,R.drawable.like_filled,R.drawable.chat,"10","10"));
        navItemModels.add(new NavItemModel("Store Name","Category Name",R.drawable.home_thumbnail,R.drawable.like,R.drawable.chat,"10","10"));

       /* navItemModels.add(new NavItemModel(R.drawable.home_thumbnail));
        navItemModels.add(new NavItemModel(R.drawable.home_thumbnail));
        navItemModels.add(new NavItemModel(R.drawable.home_thumbnail));
        navItemModels.add(new NavItemModel(R.drawable.home_thumbnail));
        navItemModels.add(new NavItemModel(R.drawable.home_thumbnail));
        navItemModels.add(new NavItemModel(R.drawable.home_thumbnail));*/

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        DashboardFragmentAdapter customAdapter = new DashboardFragmentAdapter(this,navItemModels);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }

    @Override
    public void onDashboardItemViewClick(int adapterPosition, int data) {

        Intent mIntent=new Intent(this,CategoryDetailActivity.class);
        startActivity(mIntent);

    }
}
