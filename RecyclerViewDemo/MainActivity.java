package com.example.brijesh.recyclerviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * created by :nishi on 8-01-18(monday)
 * description: recyclerview
 */
public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;
    ArrayList<RecyclerDataProvderActivity> arrayList = new ArrayList<RecyclerDataProvderActivity>();
    String[] USER_Name, USER_Description;
    //int[] IMg_recycler = {R.drawable.opd2, R.drawable.opd2, R.drawable.opd2, R.drawable.opd2, R.drawable.opd2, R.drawable.opd2, R.drawable.opd2, R.drawable.opd2};
    String[] IMG_recycler = {"https://icdn6.digitaltrends.com/image/google_icon-377x372.jpg",
            "https://s3.amazonaws.com/production-wordpress-assets/blog/wp-content/uploads/2017/09/07201801/chrome.png",
            "http://cdn.cultofmac.com/wp-content/uploads/2013/12/Screen-Shot-2013-12-29-at-7.19.00-PM-640x670.jpg",
            "https://lh3.googleusercontent.com/p5kStIWYqFXMhhVKe6TcEb4qvoXnnUlvhKWZjGNK9_U89d1bzkXHslkVrfcJrSMkeVs=w300",
            "https://tctechcrunch2011.files.wordpress.com/2013/03/research_at_google.png?w=400",
            "http://www.beevoz.com/wp-content/uploads/2014/12/Google_logo.png",
            "https://www.cheapflightsfinder.com/files/blog/000/000/004/1466880634_qlVRy_Google-Travel-Official-Image1.png",
            "https://www.seeklogo.net/wp-content/uploads/2017/04/New-Google-Earth-logo.png",
            "http://www.androidpolice.com/wp-content/uploads/2015/09/nexus2cee_GoogleLogo2.jpg"};
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);


        USER_Name = getResources().getStringArray(R.array.name_string);
        USER_Description = getResources().getStringArray(R.array.description_string);

        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        for (i = 0; i < 50; i++) {

            RecyclerDataProvderActivity recyclerDataProvderActivity = new RecyclerDataProvderActivity(IMG_recycler[0], USER_Name[0], USER_Description[0]);
            arrayList.add(recyclerDataProvderActivity);



        }


        /*for (String name : USER_Name) {
            RecyclerDataProvderActivity recyclerDataProvderActivity = new RecyclerDataProvderActivity(IMG_recycler[i], name, USER_Description[i]);
            arrayList.add(recyclerDataProvderActivity);
            i++;


        }//loop end*/


        RecyclerAdapterActivity adapter = new RecyclerAdapterActivity(MainActivity.this, arrayList);
        recyclerView.setHasFixedSize(true);
        //layoutManager = new LinearLayoutManager(this);
        // recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, GridLayoutManager.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, GridLayoutManager.VERTICAL));

    }// end onCreate()
}//end of MainActivity class
