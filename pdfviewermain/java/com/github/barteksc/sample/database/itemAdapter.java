package com.github.barteksc.sample.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.sample.PDFViewActivity;
import com.github.barteksc.sample.R;

import java.util.ArrayList;
import java.util.HashMap;

public class itemAdapter extends BaseAdapter implements View.OnClickListener
{
    PDFView pdfView;
    Context context;
    ListView listView;
    LayoutInflater inflater;
    ArrayList arraylist;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();
    CharSequence pno;
    String pdfFileName;
    int pagejump;
    int bpno;
    private int defaultPage = 0;
    PDFViewActivity pdfviewer;


    public itemAdapter(Context context, ArrayList<HashMap<String, String>> arraylist)
    {
        this.context = context;
        data = arraylist;
    }

    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public Object getItem(int position)
    {
        return data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent)
    {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = inflater.inflate(R.layout.listraw, parent, false);
        resultp = data.get(position);
        final TextView tvpage = (TextView) itemView.findViewById(R.id.tvpage);
       TextView tvbname = (TextView) itemView.findViewById(R.id.tvbname);
        tvpage.setText(resultp.get("PageNumber"));
        tvbname.setText(resultp.get("BookMarkName"));
        return itemView;


    }

       /* lvCustDetails.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                resultp = data.get(position);

                Intent intent = new Intent(context, ViewCustomerDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("CustCode", resultp.get("CustCode"));
                intent.putExtra("ExecCode", resultp.get("ExeCode"));
                intent.putExtra("CustName", resultp.get("CustName"));
                intent.putExtra("Inst1Amt", resultp.get("Inst1Amt"));
                context.startActivity(intent);
            }
        });*/




    @Override
    public void onClick(View v) {

    }
}