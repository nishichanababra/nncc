package app.citta.sales365cloud;

import android.annotation.SuppressLint;
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


    HomePageActivity activity_recycler;
    ArrayList<DataProvider> dataProvider = new ArrayList<DataProvider>();
    Context ctx1;

    public DataAdapter(ArrayList<DataProvider> dataProvider,Context ctx,HomePageActivity activity_recycler){
        this.dataProvider=dataProvider;
        this.ctx1=ctx;
        this.activity_recycler=activity_recycler;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler, parent, false);
        RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view,ctx1,dataProvider);
        return  recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DataProvider data=dataProvider.get(position);
        holder.image.setImageResource(data.getImage_id());
        holder.name.setText(data.getName());
        holder.descrip.setText(data.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataAdapterClicks ctx = (DataAdapterClicks) activity_recycler;
                ctx.onItemViewClick(dataProvider1.getImage_id(),
                        dataProvider1.getName(),
                        dataProvider1.getDescription());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataProvider.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

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
    }

    public interface DataAdapterClicks{

        void onItemViewClick(String img, String name, String description);
    }
}


