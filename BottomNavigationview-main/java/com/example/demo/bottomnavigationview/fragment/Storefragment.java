package com.example.demo.bottomnavigationview.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.demo.bottomnavigationview.MainActivity;
import com.example.demo.bottomnavigationview.R;

public class Storefragment extends Fragment {

    private TextInputLayout TNLUsername;
    private TextInputEditText TedtUsername;
    private Button btnOk;

    public Storefragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        initView(view);
        return view;

    }

    private void initView(View view) {

        TNLUsername = (TextInputLayout) view.findViewById(R.id.TNL_username);
        TedtUsername = (TextInputEditText) view.findViewById(R.id.Tedt_username);
        btnOk = (Button) view.findViewById(R.id.btn_ok);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           ((MainActivity)getActivity()).loadFragment(new Giftfragment(TNLUsername.getEditText().getText().toString().trim()));

              /*  Intent intent = new Intent("nishiTestBroadcast");
                intent.putExtra("name", TNLUsername.getEditText().getText().toString().trim());
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
                */
            }
        });
    }
}
