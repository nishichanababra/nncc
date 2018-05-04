package app.citta.utilities.GeneralizedModules.Firebase.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.citta.utilities.GeneralizedModules.Firebase.Listener.NotificationItemClickListener;
import app.citta.utilities.GeneralizedModules.Firebase.POJO.FirebaseNotificationsPOJO;
import app.citta.utilities.R;

/**
 * Created by ws-16 on 7/19/2017.
 */

public class FirebaseNotificationAdapter extends RecyclerView.Adapter<FirebaseNotificationAdapter.MyViewHolder> {

    public Context context;
    private List<FirebaseNotificationsPOJO> firebaseNotificationsPOJOs;
    private NotificationItemClickListener notificationItemClickListener;

    public FirebaseNotificationAdapter(Context context, List<FirebaseNotificationsPOJO> firebaseNotificationsPOJOs) {
        this.context = context;
        this.firebaseNotificationsPOJOs = firebaseNotificationsPOJOs;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_firebasenotifications,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        FirebaseNotificationsPOJO firebaseNotificationsPOJO = firebaseNotificationsPOJOs.get(position);

        final String id = firebaseNotificationsPOJO.getId();
        final String token = firebaseNotificationsPOJO.getFbToken();
        final String data = firebaseNotificationsPOJO.getFbData();
        final String time = firebaseNotificationsPOJO.getFbTime();
        final String status = firebaseNotificationsPOJO.getFbStatus();

        holder.tv_data.setText(data);
        holder.tv_token.setText(token);
        holder.tv_Time.setText(time);
        holder.tv_notificationsStatus.setText(status);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationItemClickListener.notificationItemClicked(id,token,data,time,status,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return firebaseNotificationsPOJOs.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_notificationsStatus,tv_data,tv_token,tv_Time;
        MyViewHolder(View itemView) {
            super(itemView);
            tv_notificationsStatus = (TextView) itemView.findViewById(R.id.tv_notificationStatus);
            tv_data = (TextView) itemView.findViewById(R.id.tv_notificationData);
            tv_token = (TextView) itemView.findViewById(R.id.tv_firebaseToken);
            tv_Time = (TextView) itemView.findViewById(R.id.tv_notificationTime);
        }
    }

    public void registerNotificationListener(NotificationItemClickListener notificationItemClickListener){
        this.notificationItemClickListener = notificationItemClickListener;
    }
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
