package com.app.citta.sukhnidayri.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.citta.sukhnidayri.R;
import com.app.citta.sukhnidayri.activity.OpenBookMark;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<OpenBookMark.BookData> arraylist;

    public ItemAdapter(Context context, ArrayList<OpenBookMark.BookData> arraylist) {
        this.context = context;
        this.arraylist = arraylist;
    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        @SuppressLint("ViewHolder") final View itemView = inflater.inflate(R.layout.adapter_bookmark_list, parent, false);

        TextView tvpage = (TextView) itemView.findViewById(R.id.tvpage);
        TextView tvbname = (TextView) itemView.findViewById(R.id.tvbname);
        tvpage.setText(arraylist.get(position).getPageNo());
        tvbname.setText(arraylist.get(position).getbName());
        return itemView;
    }
}