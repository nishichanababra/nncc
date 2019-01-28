package com.android.practicaltest;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class DashboardFragment extends Fragment implements
        DashboardFragmentAdapter.OnItemClickListener{

    private RecyclerView dashBoardMenuItemRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private ArrayList<NavItemModel> navItemModels = new ArrayList<>();

    private DashboardFragmentAdapter dashboardFragmentAdapter;
    View v = null;

    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_item_one, container, false);
        initView(v);
        return v;
    }

    @SuppressLint("SetTextI18n")
    private void initView(View v) {

        dashBoardMenuItemRecyclerView = v.findViewById(R.id.card_recycler_view);

        navItemModels.add(new NavItemModel(R.drawable.home_thumbnail));
        navItemModels.add(new NavItemModel(R.drawable.home_thumbnail));
        navItemModels.add(new NavItemModel(R.drawable.home_thumbnail));
        navItemModels.add(new NavItemModel(R.drawable.home_thumbnail));
        navItemModels.add(new NavItemModel(R.drawable.home_thumbnail));
        navItemModels.add(new NavItemModel(R.drawable.home_thumbnail));

    }



    @Override
    public void onDashboardItemViewClick(int adapterPosition, int data) {

    }
}
