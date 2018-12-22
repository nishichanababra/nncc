package app.citta.retail365cloud.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.citta.retail365cloud.R;
import app.citta.retail365cloud.fragment.DashboardFragment;
import app.citta.retail365cloud.fragment.ReportsFragment;
import app.citta.retail365cloud.models.DateDialogModel;

public class DateDialogAdapter extends RecyclerView.Adapter<DateDialogAdapter.MyViewHolder> {

    private Context mContext;
    private DashboardFragment mContextForDashboard;
    private ReportsFragment mContextForReports;
    private ArrayList<DateDialogModel> dateDialogModels;



    private String contextSetFor = "", dashboardFragment = "DashboardFragment", reportsFragment = "ReportsFragment";

    public DateDialogAdapter(DashboardFragment mContextForDashboard, ArrayList<DateDialogModel> dateDialogModels) {
        this.mContextForDashboard = mContextForDashboard;
        this.dateDialogModels = dateDialogModels;
        contextSetFor=dashboardFragment;
    }

    public DateDialogAdapter(ReportsFragment mContextForReports, ArrayList<DateDialogModel> dateDialogModels) {
        this.mContextForReports = mContextForReports;
        this.dateDialogModels = dateDialogModels;
        contextSetFor=reportsFragment;
    }

    public DateDialogAdapter(Context mContext, ArrayList<DateDialogModel> dateDialogModels) {
        this.mContext = mContext;
        this.dateDialogModels = dateDialogModels;
    }

    @Override
    public DateDialogAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_datetime_dialog, parent, false);
        return new DateDialogAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(DateDialogAdapter.MyViewHolder holder, int position) {

        holder.tv_list.setText(dateDialogModels.get(position).getItemName());

        if (dateDialogModels.get(position).isSelected())
            holder.img_check.setVisibility(View.VISIBLE);

        else holder.img_check.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(v -> {

            OnItemClickListener clickListener;

            if (contextSetFor.equals(dashboardFragment)) {
                clickListener = (OnItemClickListener) mContextForDashboard;
            } else if(contextSetFor.equals(reportsFragment)){
                clickListener = (OnItemClickListener) mContextForReports;
            }
            else
            {
                clickListener = (OnItemClickListener) mContext;
            }
            clickListener.onItemViewClick(holder.getAdapterPosition(), dateDialogModels.get(position).getItemName());

        });
    }

    @Override
    public int getItemCount() {
        return dateDialogModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_list, img_check;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_list = itemView.findViewById(R.id.tv_list);
            img_check = itemView.findViewById(R.id.tv_check);

        }
    }

    public interface OnItemClickListener {

        void onItemViewClick(int adapterPosition, String data);
    }


}
