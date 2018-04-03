package com.company.ecommerce.recyclerviewdemo;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    Context context;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<DataProvider> list = new ArrayList<DataProvider>();

    int[] image_id = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};
    String[] name, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = getResources().getStringArray(R.array.name);
        description = getResources().getStringArray(R.array.description);
        int count = 0;
        for (String Name : name) {

            DataProvider dataProvider = new DataProvider(image_id[count], Name, description[count]);
            count++;
            list.add(dataProvider);


        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new DataAdapter(list, context);
        recyclerView.setAdapter(adapter);


        //toolbar=(Toolbar)findViewById(R.id.toolbar);
        //toolbar.setTitle(getResources().getString((R.string.app_name)));

        // listView = (ListView) findViewById(R.id.listview);
      /*  DataAdapter adapter = new DataAdapter(this);

        listView.setAdapter(adapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),RecyclerActivity.class);
                intent.putExtra("Position",listView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });*/
    }
}
