package com.github.barteksc.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.github.barteksc.sample.database.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class BookMarkList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarklist);

        TextView tvpage,tvbname;
        ListView listView;

        tvpage = (TextView) findViewById(R.id.tvpage);
        tvbname = (TextView) findViewById(R.id.tvbname);


       /* DBHelper dbHelper=new DBHelper(BookMarkList.this);
        ArrayList<HashMap<String, String>> userList = dbHelper.GetUsers();
        ListView lv = (ListView) findViewById(R.id.listview);
        ListAdapter adapter = new SimpleAdapter(BookMarkList.this, userList, R.layout.listraw,new String[]{"page","bname"}, new int[]{R.id.tvpage, R.id.tvbname});
        lv.setAdapter(adapter);*/
    }

}
