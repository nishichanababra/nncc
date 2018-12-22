package app.citta.retail365cloud.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.citta.retail365cloud.R;
import app.citta.retail365cloud.apimodels.SalesReportObject;

public class SalesChildReportAdapter extends RecyclerView.Adapter<SalesChildReportAdapter.MyViewHolder> {

    private ArrayList<SalesReportObject.ListProduct> salesReportObjects;
    private Context mContext;
    private SalesReportAdapter salesReportAdapter;

    public SalesChildReportAdapter(ArrayList<SalesReportObject.ListProduct> salesReportObjects, Context mContext) {
        this.salesReportObjects = salesReportObjects;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sales_report_child, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        SalesReportObject.ListProduct product = salesReportObjects.get(position);

        holder.tv_salesno.setText("#"+String.valueOf(position+1));
        holder.tv_prodname.setText(product.getProductname());
        holder.tv_qty.setText(product.getQty());
        holder.tv_unitprice.setText(product.getUnitprice());
        holder.tv_discount.setText(product.getDiscount());
        holder.tv_gst.setText(product.getGst());
        holder.tv_total.setText(product.getTotalamount());
    }

    @Override
    public int getItemCount() {
        return salesReportObjects.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_salesno,tv_prodname, tv_qty, tv_unitprice, tv_discount, tv_gst, tv_total;
        public RecyclerView recycler_view_child;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_salesno=itemView.findViewById(R.id.tv_salesno);
            tv_prodname = itemView.findViewById(R.id.tv_prodname);
            tv_qty = itemView.findViewById(R.id.tv_qty);
            tv_unitprice = itemView.findViewById(R.id.tv_unitprice);
            tv_discount = itemView.findViewById(R.id.tv_discount);
            tv_gst = itemView.findViewById(R.id.tv_gst);
            tv_total = itemView.findViewById(R.id.tv_total);
        }
    }
}
