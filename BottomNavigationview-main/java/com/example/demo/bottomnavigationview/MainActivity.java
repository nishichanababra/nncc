package com.example.demo.bottomnavigationview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;

import com.example.demo.bottomnavigationview.fragment.Cartfragment;
import com.example.demo.bottomnavigationview.fragment.Giftfragment;
import com.example.demo.bottomnavigationview.fragment.Peoplefragment;
import com.example.demo.bottomnavigationview.fragment.Storefragment;


public class MainActivity extends AppCompatActivity {


    Fragment fragment;
    private ActionBar toolbar;
    BottomNavigationView navigation;
    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {

                case R.id.navigation_shop:
                    toolbar.setTitle("Shop");
                    fragment = new Storefragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_gifts:
                    toolbar.setTitle("My Gifts");
                    fragment = new Giftfragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_cart:
                    toolbar.setTitle("Cart");
                    fragment = new Cartfragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_profile:
                    toolbar.setTitle("Profile");
                    fragment = new Peoplefragment();
                    loadFragment(fragment);
                    return true;

            }
            return false;
        }
    };


   /* public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent != null) {


            }
        }
    };*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("nishiTestBroadcast"));

        toolbar = getSupportActionBar();

         navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        toolbar.setTitle("Shop");

        loadFragment(new Storefragment());
    }

    public void loadFragment(Fragment fragment) {
        // load fragment

        if(fragment instanceof Giftfragment){
            navigation.getMenu().getItem(1).setChecked(true);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);


        transaction.commit();
    }
}