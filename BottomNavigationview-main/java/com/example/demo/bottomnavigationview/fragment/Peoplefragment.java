package com.example.demo.bottomnavigationview.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo.bottomnavigationview.R;

public class Peoplefragment extends android.support.v4.app.Fragment {

    public Peoplefragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    public static Peoplefragment newInstance(String param1, String param2) {
        Peoplefragment fragment = new Peoplefragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

}
