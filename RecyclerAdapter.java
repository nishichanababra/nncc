package com.company.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.company.ecommerce.ProductDescriptionActivity;
import com.company.ecommerce.R;

import java.util.ArrayList;

/**
 * Created by Brijesh on 09-01-18.
 * description: Recycler adapter class
 */


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> implements View.OnClickListener {
    private final String TAG = "RecyclerAdapter";
    private Context context;
    private ArrayList<DataProvider> arrayList = new ArrayList<DataProvider>();

    // Constructor
    public RecyclerAdapter(Context context, ArrayList<DataProvider> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }// end of constructor

    // onCreateViewHolder method
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_home, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }// end of onCreateViewHolder method

    // onBindViewHolder method
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: "+position);
        DataProvider dataProvider = arrayList.get(position);
        Log.d(TAG, "getItemCount: " + dataProvider.getImgrecycler());
        holder.imgrecycler.setImageResource(dataProvider.getImgrecycler());
        // Picasso.with(context).load(arrayList.get(position).getImgrecycler()).resize(240, 120).into(holder.imgrecycler);
      /*  holder.name.setText("Username:" + position);*/
        holder.name.setText(arrayList.get(position).getName());
        holder.price.setText(holder.price.getResources().getString(R.string.priceicon) + arrayList.get(position).getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDescriptionActivity.class);
                context.startActivity(intent);
            }
        });

    }//  end onBindViewHolder method

    //getItemCount method
    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + arrayList.size());
        return arrayList.size();

    }    // end getItemCount method

    @Override
    public void onClick(View view) {
    }


    // MyViewHolder class
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgrecycler;
        private TextView name;
        private TextView price;

        public MyViewHolder(View view) {
            super(view);

            imgrecycler = (ImageView) view.findViewById(R.id.imgrecycler);
            name = (TextView) view.findViewById(R.id.txtname);
            price = (TextView) view.findViewById(R.id.txtPrice);
        }//end MyViewHolder constructor
    }//end of MyViewHolder class
}
//end of Adapter class
