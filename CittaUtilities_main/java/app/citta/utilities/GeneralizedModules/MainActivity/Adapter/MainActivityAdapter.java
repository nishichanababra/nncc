package app.citta.utilities.GeneralizedModules.MainActivity.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.citta.utilities.GeneralizedModules.MainActivity.Listener.MainActivityItemListeners;
import app.citta.utilities.GeneralizedModules.MainActivity.POJO.mainActivityPOJO;
import app.citta.utilities.R;

/**
 * Created by ws-16 on 7/17/2017.
 */

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MyViewHolder> {

    private Context context;
    List<mainActivityPOJO> mainActivityPOJOs;
    MainActivityItemListeners mainActivityItemListeners;

    public MainActivityAdapter(Context context,List<mainActivityPOJO> mainActivityPOJOs) {
        this.context = context;
        this.mainActivityPOJOs = mainActivityPOJOs;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_main,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        mainActivityPOJO mainActivityPOJO = mainActivityPOJOs.get(position);

        final String itemName = mainActivityPOJO.getItemName();
        final int itemPosition = mainActivityPOJO.getPosition();

        holder.tv_main.setText(itemName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityItemListeners.mainActivityItemClickListner(itemName,itemPosition);
            }
        });
    }
    public void registerMainActivityItemClick(MainActivityItemListeners mainActivityItemListeners){
        this.mainActivityItemListeners = mainActivityItemListeners;
    }

    @Override
    public int getItemCount() {
        return mainActivityPOJOs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_main;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_main = (TextView)itemView.findViewById(R.id.tv_main);
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
