package com.mandaliyamedicals.medical.userFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import com.mandaliyamedicals.medical.databinding.ViewDashboardBannersBinding;
import com.mandaliyamedicals.medical.models.dashbaord.DashboardBanner;

/**
 * Created by asama on 10-04-2017.
 */
@SuppressLint("ValidFragment")
public class DashboardBannerFragment extends Fragment {
    private int mPosition;
    private List<DashboardBanner> mDashboardBannerInfos;

    public DashboardBannerFragment() {
    }

    public DashboardBannerFragment(List<DashboardBanner> dashboardBannerInfos, int i) {
        mDashboardBannerInfos = dashboardBannerInfos;
        mPosition = i;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDashboardBannersBinding binding = ViewDashboardBannersBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        if (mDashboardBannerInfos != null) {
            Glide.with(getContext()).load(mDashboardBannerInfos.get(mPosition).getBannerPhoto())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.imgOffers);
        }

        return view;
    }
}