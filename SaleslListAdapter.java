package app.citta.retail365cloud.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.citta.retail365cloud.R;
import app.citta.retail365cloud.apimodels.SalesListObject;
import app.citta.retail365cloud.apimodels.SalesOrderEditObject;

import static app.citta.retail365cloud.abstractclasses.BaseActivity.CurrentFont;
import static app.citta.retail365cloud.utils.CommonFunctions.dateConversion;

public class SaleslListAdapter extends RecyclerView.Adapter<SaleslListAdapter.MyViewHolder> {

    private ArrayList<SalesListObject> salesListObjects;
    private SalesOrderEditObject salesOrderEditObject;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_salesno, tv_orderno, tv_qty, tv_discount, tv_gtotal, tv_name, tv_Odate, ivEditemployee, ivDeleteemployee, ivViewemployee;

        public MyViewHolder(View view) {
            super(view);

            tv_salesno = itemView.findViewById(R.id.tv_salesno);
            tv_orderno = itemView.findViewById(R.id.tv_orderno);
            tv_qty = itemView.findViewById(R.id.tv_qty);
            tv_discount = itemView.findViewById(R.id.tv_discount);
            tv_gtotal = itemView.findViewById(R.id.tv_gtotal);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_Odate = itemView.findViewById(R.id.tv_Odate);

            ivViewemployee = itemView.findViewById(R.id.iv_view_employee);
            ivEditemployee = itemView.findViewById(R.id.iv_edit_employee);
            ivDeleteemployee = itemView.findViewById(R.id.iv_delete_employee);
            Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), CurrentFont);
            ivEditemployee.setTypeface(typeface);
            ivDeleteemployee.setTypeface(typeface);
            ivViewemployee.setTypeface(typeface);

            ivEditemployee.setText(mContext.getResources().getString(R.string.icon_edit));
            ivDeleteemployee.setText(mContext.getResources().getString(R.string.icon_delete));
            ivViewemployee.setText(mContext.getResources().getString(R.string.icon_eye));

        }
    }

    public SaleslListAdapter(ArrayList<SalesListObject> salesListObjects, Context mContext) {
        this.salesListObjects = salesListObjects;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sales_list, parent, false);
        return new SaleslListAdapter.MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        SalesListObject salesListObject = salesListObjects.get(position);

        holder.tv_salesno.setText("#" + String.valueOf(position + 1) + ".");
        holder.tv_orderno.setText(salesListObject.getOrderno());
        holder.tv_qty.setText(salesListObject.getQty());
        holder.tv_discount.setText(salesListObject.getDiscount());
        holder.tv_gtotal.setText(salesListObject.getGrandtotal());
        holder.tv_name.setText(salesListObject.getCustomername());

        if (!TextUtils.isEmpty(salesListObject.getOrderdatetime()))
            holder.tv_Odate.setText(dateConversion("MM/dd/yyyy hh:mm:ss aa", "dd/MM/yyyy", salesListObject.getOrderdatetime()));
        else
            holder.tv_Odate.setText("");

        holder.ivViewemployee.setOnClickListener(view -> {

            SalesItemClickListener itemClickListener = (SalesItemClickListener) mContext;
            itemClickListener.onSalesViewClick(holder.getAdapterPosition(), salesListObject);

        });

        holder.ivEditemployee.setOnClickListener(view -> {

            SalesItemClickListener salesItemClickListener = (SalesItemClickListener) mContext;
            salesItemClickListener.onSalesEditClick(salesListObject.getSalesorderid());

        });

        holder.ivDeleteemployee.setOnClickListener(view -> {

            SalesItemClickListener itemClickListener = (SalesItemClickListener) mContext;
            itemClickListener.onSalesDeleteClick(holder.getAdapterPosition(), salesListObject);

        });
    }

    @Override
    public int getItemCount() {
        return salesListObjects.size();
    }

    public interface SalesItemClickListener {

        void onSalesViewClick(int adapterPosition, SalesListObject salesListObject);

        //void onSalesEditClick(SalesListObject salesListObject);

        void onSalesDeleteClick(int adapterPosition, SalesListObject salesListObject);

        void onSalesEditClick(String orderID);
    }
}