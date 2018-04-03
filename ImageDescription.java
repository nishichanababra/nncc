package com.company.ecommerce.recyclerviewdemo;

/**
 * Created by Brijesh on 03-04-18.
 */


import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

public class ImageDescription  extends AppCompatActivity{

    ImageView imageView;
    TextView name,description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagedescripton);

        imageView=findViewById(R.id.imageview);
        name=findViewById(R.id.name);
        description=findViewById(R.id.description);

        imageView.setImageResource(getIntent().getIntExtra("img",00));
        name.setText("name:"+getIntent().getStringExtra("name"));
        description.setText(("description"+getIntent().getStringExtra("description")));

    }
}
