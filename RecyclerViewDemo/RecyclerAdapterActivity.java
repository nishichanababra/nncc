package com.example.brijesh.recyclerviewdemo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Brijesh on 09-01-18.
 * description: Recycler adapter class
 */


public class RecyclerAdapterActivity extends RecyclerView.Adapter<RecyclerAdapterActivity.MyViewHolder> {
    private final String TAG = "RecyclerDataProvderActivity";
    private Context context;
    private ArrayList<RecyclerDataProvderActivity> arrayList = new ArrayList<RecyclerDataProvderActivity>();

    // Constructor
    public RecyclerAdapterActivity(Context context, ArrayList<RecyclerDataProvderActivity> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }// end of constructor

    // onCreateViewHolder method
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }// end of onCreateViewHolder method

    // onBindViewHolder method
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RecyclerDataProvderActivity recyclerDataProvderActivity = arrayList.get(position);
        // holder.imgrecycler.setImageResource(recyclerDataProvderActivity.getImgrecycler());
        holder.name.setText("Username:" + position);
        holder.description.setText("User Description:" + position);
        //Log.d(TAG, "Image path :: " + recyclerDataProvderActivity.getImgrecycler());
        try {
            Picasso.with(context).load( "https://www.google.co.in/search?q=cartoon+images&client=firefox-b-ab&dcr=1&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjd0cK_jc3YAhWHpo8KHdBdBVkQ_AUICigB&biw=1366&bih=656"/*recyclerDataProvderActivity.getImgrecycler()*/).resize(200, 180).centerCrop().into(holder.imgrecycler);//image loading with picasso library into gradle file
        } catch (Exception e) {
            e.printStackTrace();

        }

    }//  end onBindViewHolder method

    //getItemCount method
    @Override
    public int getItemCount() {
        return arrayList.size();
    }    // end getItemCount method


    // MyViewHolder class
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgrecycler;
        private TextView name;
        private TextView description;

        public MyViewHolder(View view) {
            super(view);

            imgrecycler = (ImageView) view.findViewById(R.id.imgrecycler);
            name = (TextView) view.findViewById(R.id.name);
            description = (TextView) view.findViewById(R.id.description);
        }//end MyViewHolder constructor
    }//end of MyViewHolder class
}
//end of Adapter class
