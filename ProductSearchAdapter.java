package com.mandaliyamedicals.medical.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mandaliyamedicals.medical.databinding.ViewProductSearchBinding;
import com.mandaliyamedicals.medical.generalHelper.Constants;
import com.mandaliyamedicals.medical.models.ProductSearchHistoryInfo;
import com.mandaliyamedicals.medical.models.ProductSearchInfo;
import com.mandaliyamedicals.medical.userActivities.ProductDetailsActivity;
import com.mandaliyamedicals.medical.userActivities.ProductSearchActivity;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asama on 10-04-2017.
 * ProductSearchAdapter
 */

public class ProductSearchAdapter extends RecyclerView.Adapter<ProductSearchAdapter.MyViewHolder> {
    private Context mContext;
    private List<ProductSearchInfo> mProductSearchInfo;
    private List<ProductSearchHistoryInfo> mProductSearchHistoryInfos;
    private String intent;

    public ProductSearchAdapter(Context context, List<ProductSearchInfo> productSearchHistoryInfos, String intent) {
        mContext = context;
        mProductSearchInfo = productSearchHistoryInfos;
        this.intent = intent;
    }

    @Override
    public ProductSearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        ViewProductSearchBinding viewProductSearchBinding = ViewProductSearchBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(viewProductSearchBinding);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ProductSearchInfo productSearchInfo = mProductSearchInfo.get(position);

        holder.binding.txtProductName.setText(productSearchInfo.getProductName());
        //  holder.binding.txtPackage.setText(productSearchInfo.getProductPackage());
        holder.binding.txtSubstitute.setText(productSearchInfo.getCount_of_substitute_product() + " variants");


        holder.binding.llMedicineList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addToSearchHistory(productSearchInfo.getProductId(), productSearchInfo.getProductName(), productSearchInfo.getProductPackage());
                    }
                });

                mContext.startActivity(new Intent(mContext, ProductDetailsActivity.class)
                        .putExtra("medicine_id", productSearchInfo.getProductId())
                        .putExtra("medicine_name", productSearchInfo.getProductName())
                        .putExtra("RefillMedicine", intent)
                        .putExtra("otc", ((ProductSearchActivity) mContext).getIntent().getStringExtra("otc")));

            }
        });

        holder.binding.imgRemove.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mProductSearchInfo.size();
    }

    private void addToSearchHistory(String entityId, String name, String packageName) {
        if (Hawk.contains(Constants.ARG_SEARCH_HISTORY)) {
            mProductSearchHistoryInfos = Hawk.get(Constants.ARG_SEARCH_HISTORY);
            if (!thisEntityIdInHistory(entityId)) {
                ProductSearchHistoryInfo productSearchHistoryInfo = new ProductSearchHistoryInfo();
                productSearchHistoryInfo.setProductId(entityId);
                productSearchHistoryInfo.setProductName(name);
                productSearchHistoryInfo.setProductPackage(packageName);
                mProductSearchHistoryInfos.add(productSearchHistoryInfo);
                Hawk.put(Constants.ARG_SEARCH_HISTORY, mProductSearchHistoryInfos);
            }
        } else {
            mProductSearchHistoryInfos = new ArrayList<>();
            ProductSearchHistoryInfo productSearchHistoryInfo = new ProductSearchHistoryInfo();
            productSearchHistoryInfo.setProductId(entityId);
            productSearchHistoryInfo.setProductName(name);
            productSearchHistoryInfo.setProductPackage(packageName);
            mProductSearchHistoryInfos.add(productSearchHistoryInfo);
            Hawk.put(Constants.ARG_SEARCH_HISTORY, mProductSearchHistoryInfos);
        }
    }

    private boolean thisEntityIdInHistory(String entityId) {
        for (int i = 0; i < mProductSearchHistoryInfos.size(); i++) {
            if (mProductSearchHistoryInfos.get(i).getProductId().equalsIgnoreCase(entityId)) {
                return true;
            }
        }
        return false;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ViewProductSearchBinding binding;

        public MyViewHolder(ViewProductSearchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
