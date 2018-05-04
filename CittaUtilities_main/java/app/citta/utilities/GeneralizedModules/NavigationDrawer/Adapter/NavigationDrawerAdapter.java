package app.citta.utilities.GeneralizedModules.NavigationDrawer.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import app.citta.utilities.GeneralizedModules.NavigationDrawer.POJO.NavDrawerItemModel;
import app.citta.utilities.R;


public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavDrawerItemModel> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public NavigationDrawerAdapter(Context context, List<NavDrawerItemModel> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItemModel current = data.get(position);
        holder.title.setText(current.getTitle());

        if (position == 0) {
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.home));
        } else if (position == 3) {
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.examschedule));
        } else if (position == 4) {
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.examresult));
        } else if (position == 2) {
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.date));
        } else if (position == 1) {
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.profile));
        } else if (position == 7) {
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.password));
        } else if (position == 6) {
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.history));
        } else if (position == 5) {
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.date));
        }else if (position == 8) {
            holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.logout));
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}