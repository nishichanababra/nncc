package com.example.demo.bottomnavigationview.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.bottomnavigationview.R;

public class Giftfragment extends android.support.v4.app.Fragment {

    String s;
    private TextView textview_gift;
    public Giftfragment() {

    }

    @SuppressLint("ValidFragment")
    public Giftfragment(String s) {
        this.s = s;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gift, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        textview_gift = (TextView) view.findViewById(R.id.textview_gift);
        textview_gift.setText(s);

    }

    }


