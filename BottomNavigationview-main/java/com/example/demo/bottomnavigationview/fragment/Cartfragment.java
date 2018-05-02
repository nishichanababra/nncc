package com.example.demo.bottomnavigationview.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo.bottomnavigationview.R;

public class Cartfragment extends android.support.v4.app.Fragment {

    public Cartfragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    public static Cartfragment newInstance(String param1, String param2) {
        Cartfragment fragment = new Cartfragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
}
