package com.company.ecommerce.recyclerviewdemo;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Brijesh on 02-04-18.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.RecyclerViewHolder> {


    ArrayList<DataProvider> dataProvider=new ArrayList<DataProvider>();
    Context ctx1;

    public DataAdapter(ArrayList<DataProvider> dataProvider,Context ctx){
        this.dataProvider=dataProvider;
        this.ctx1=ctx;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler, parent, false);
        RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view,ctx1,dataProvider);
        return  recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        DataProvider data=dataProvider.get(position);
        holder.image.setImageResource(data.getImage_id());
        holder.name.setText(data.getName());
        holder.descrip.setText(data.getDescription());



    }

    @Override
    public int getItemCount() {
        return dataProvider.size();
    }

   /* Context c;
    String[] names={"paper1","paper2","paper3","paper4"};
    String[] desc={"paper1","paper2","paper3","paper4"};
    int[] images={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4};


    public  DataAdapter(Context cx){
        this.c=cx;
    }

    @Override
    public int getCount() {
        return  names.length;
    }

    @Override
    public Object getItem(int i) {
       return names[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.imagedescripton,null);
        }
        TextView name=(TextView)view.findViewById(R.id.name);
        TextView description=(TextView)view.findViewById(R.id.description);
        ImageView image=(ImageView)view.findViewById(R.id.imageview);


        name.setText(names[i]);
        description.setText(desc[i]);
        image.setImageResource(images[i]);



        return view;
    }*/

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        ImageView image;
        TextView name,descrip;
        ArrayList<DataProvider> dataProviders=new ArrayList<DataProvider>();
        Context ctx;
        public RecyclerViewHolder(View itemView,Context ctx,ArrayList<DataProvider> dataProvider) {
            super(itemView);
            this.dataProviders=dataProvider;
            this.ctx=ctx;
            itemView.setOnClickListener(this);
            name=(TextView)itemView.findViewById(R.id.name);
            descrip=(TextView)itemView.findViewById(R.id.description);
            image=(ImageView)itemView.findViewById(R.id.imageview);
        }

        @Override
        public void onClick(View view) {

            int position=getAdapterPosition();
            DataProvider dataProvider1=this.dataProviders.get(position);
            Intent intent=new Intent(ctx,ImageDescription.class);
            intent.putExtra("img",dataProvider1.getImage_id());
            intent.putExtra("name",dataProvider1.getName());
            intent.putExtra("description",dataProvider1.getDescription());
            this.ctx.startActivity(intent);
        }
    }
}
