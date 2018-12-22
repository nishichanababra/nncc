package app.citta.retail365cloud.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.chuross.library.ExpandableLayout;

import java.util.ArrayList;

import app.citta.retail365cloud.R;
import app.citta.retail365cloud.apimodels.SalesReportObject;

import static app.citta.retail365cloud.utils.CommonFunctions.dateConversion;

public class SalesReportAdapter extends RecyclerView.Adapter<SalesReportAdapter.MyViewHolder> {

    private ArrayList<SalesReportObject> salesReportObjects;
    private Context mContext;

    public SalesReportAdapter(ArrayList<SalesReportObject> salesReportObjects, Context mContext) {
        this.salesReportObjects = salesReportObjects;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sales_report, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_salesno.setText("#" + String.valueOf(position + 1));
        holder.tv_orderno.setText(salesReportObjects.get(position).getOrderno());
        holder.tv_name.setText(salesReportObjects.get(position).getName());
        holder.tv_discount.setText(salesReportObjects.get(position).getDiscount());
        holder.tv_gst.setText(salesReportObjects.get(position).getGrandGST());
        holder.tv_total.setText(salesReportObjects.get(position).getGrandtotal());

        ArrayList<SalesReportObject.ListProduct> listOfProducts = salesReportObjects.get(position).getListProducts();

        SalesChildReportAdapter salesChildReportAdapter = new SalesChildReportAdapter(listOfProducts, mContext);
        holder.rv_child.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rv_child.setItemAnimator(new DefaultItemAnimator());
        holder.rv_child.setAdapter(salesChildReportAdapter);

        if (!TextUtils.isEmpty(salesReportObjects.get(position).getOrderdate()))
            holder.tv_Odate.setText(dateConversion("MM/dd/yyyy hh:mm:ss aa", "dd/MM/yyyy", salesReportObjects.get(position).getOrderdate()));
        else
            holder.tv_Odate.setText("");


        holder.rlticons.setOnClickListener(view -> {
            if(holder.expandableLayout.isExpanded()) {
                holder.img_expand_imageview.setImageResource(R.drawable.ic_down_arrow);
                holder.expandableLayout.collapse();
            }else{
                holder.img_expand_imageview.setImageResource(R.drawable.ic_up_arrow);
                holder.expandableLayout.expand();
            }
        });
    }

    @Override
    public int getItemCount() {
        return salesReportObjects.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RecyclerView rv_child;
        TextView tv_salesno, tv_orderno, tv_name, tv_Odate, tv_discount, tv_gst, tv_total, tv_down_arrow;
        private NestedScrollView nestedScrollView;
        private RelativeLayout rlticons;
        private ImageView img_expand_imageview;
        private ExpandableLayout expandableLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            img_expand_imageview = itemView.findViewById(R.id.img_expand_imageview);
            rlticons = itemView.findViewById(R.id.rlticons);
            tv_salesno = itemView.findViewById(R.id.tv_salesno);
            tv_orderno = itemView.findViewById(R.id.tv_orderno);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_Odate = itemView.findViewById(R.id.tv_Odate);
            tv_discount = itemView.findViewById(R.id.tv_discount);
            tv_gst = itemView.findViewById(R.id.tv_gst);
            tv_total = itemView.findViewById(R.id.tv_total);

            rv_child = itemView.findViewById(R.id.rv_sales);
            nestedScrollView = itemView.findViewById(R.id.ns_salesreport);
           // tv_down_arrow = itemView.findViewById(R.id.tv_down_arrow);
        }
    }

  /*  public interface ExpandableRecyclerviewListener {

        void showRecyclerData(int position);
    }
*/
}
