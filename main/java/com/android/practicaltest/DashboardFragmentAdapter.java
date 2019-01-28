package com.android.practicaltest;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashboardFragmentAdapter extends RecyclerView.Adapter<DashboardFragmentAdapter.MyViewHolder> {

    private ImageCategoryActivity mContext;
    private ArrayList<NavItemModel> navItemModels;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvStore, tvCategory,tvLike,tvChat;
        private RelativeLayout rl_dashdetail;
        ImageView iv_icon,iv_Like,iv_chat;
        private CardView cardview;
        private RelativeLayout rv_main_container;

        public MyViewHolder(View view) {
            super(view);

            iv_icon = view.findViewById(R.id.iv_icon);
            rl_dashdetail = view.findViewById(R.id.rv_sub_container);
            tvCategory=view.findViewById(R.id.tv_Catnm);
            tvChat=view.findViewById(R.id.tv_chat);
            tvLike=view.findViewById(R.id.tv_like);
            iv_Like=view.findViewById(R.id.iv_like);
            iv_chat=view.findViewById(R.id.iv_msg);
            tvStore=view.findViewById(R.id.tvStore);
        }
    }

    public DashboardFragmentAdapter(ImageCategoryActivity mContext, ArrayList<NavItemModel> navItemModels) {
        this.mContext = mContext;
        this.navItemModels = navItemModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recyclerview_home, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.iv_icon.setImageResource(navItemModels.get(position).getMicon());
        holder.tvStore.setText(navItemModels.get(position).getStrStore());
        holder.tvCategory.setText(navItemModels.get(position).getStrCategory());
        holder.tvLike.setText(navItemModels.get(position).getLike());
        holder.tvChat.setText(navItemModels.get(position).getChat());
        holder.iv_Like.setImageResource(navItemModels.get(position).getLikeIcon());
        holder.iv_chat.setImageResource(navItemModels.get(position).getChatIcon());

        holder.iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OnItemClickListener onItemClickListener= (OnItemClickListener) mContext;
                onItemClickListener.onDashboardItemViewClick(holder.getAdapterPosition(),navItemModels.get(position).getMicon());
            }
        });

    }

    @Override
    public int getItemCount() {
        return navItemModels.size();
    }

    public interface OnItemClickListener {

        void onDashboardItemViewClick(int adapterPosition, int data);
    }
}