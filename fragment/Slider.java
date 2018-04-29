package com.company.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.company.ecommerce.DesigningActivity;
import com.company.ecommerce.FrameActivity;
import com.company.ecommerce.LampShadeActivity;
import com.company.ecommerce.LoginActivity;
import com.company.ecommerce.ModArtsActivity;
import com.company.ecommerce.MyAccountActivity;
import com.company.ecommerce.PaintingActivity;
import com.company.ecommerce.R;
import com.company.ecommerce.RegistrationActivity;
import com.company.ecommerce.ShowPieceActivity;


/***
 * Project created by Jyoti on 19 Jan 2018 Friday
 *Navigation drwaer of the application
 */
public class Slider extends BaseFragment {


    private String TAG = "Slider";
    private TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slider, container, false);

        initUIControls(view);

        registerForListener();

        setUIData();

        return view;
    }

    @Override
    protected void initUIControls(View view) {
        textView1 = view.findViewById(R.id.txtv_modarts);
        textView2 = view.findViewById(R.id.txtv_lampshades);
        textView3 = view.findViewById(R.id.txtv_painting);
        textView4 = view.findViewById(R.id.txtv_frame);
        textView5 = view.findViewById(R.id.txtv_showpiece);
        textView6 = view.findViewById(R.id.txtv_designing);
        textView7 = view.findViewById(R.id.txtv_login);
        textView8 = view.findViewById(R.id.txtv_registration);
        textView9 = view.findViewById(R.id.txtv_myaccount);
        textView10 = view.findViewById(R.id.txtv_logout);
    }

    @Override
    protected void registerForListener() {
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        textView6.setOnClickListener(this);
        textView7.setOnClickListener(this);
        textView8.setOnClickListener(this);
        textView9.setOnClickListener(this);
        textView10.setOnClickListener(this);

    }

    @Override
    protected void setUIData() {
        super.setUIData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.txtv_modarts:
                Intent modarts = new Intent(getActivity(), ModArtsActivity.class);
                startActivity(modarts);
                getActivity().finish();
                break;

            case R.id.txtv_lampshades:
                Intent lampshades = new Intent(getActivity(), LampShadeActivity.class);
                startActivity(lampshades);
                getActivity().finish();
                break;

            case R.id.txtv_painting:
                Intent painting = new Intent(getActivity(), PaintingActivity.class);
                startActivity(painting);
                getActivity().finish();
                break;

            case R.id.txtv_frame:
                Intent frame = new Intent(getActivity(), FrameActivity.class);
                startActivity(frame);
                getActivity().finish();
                break;

            case R.id.txtv_showpiece:
                Intent showpiece = new Intent(getActivity(), ShowPieceActivity.class);
                startActivity(showpiece);
                getActivity().finish();
                break;

            case R.id.txtv_designing:
                Intent designing = new Intent(getActivity(), DesigningActivity.class);
                startActivity(designing);
                getActivity().finish();
                break;

            case R.id.txtv_myaccount:
                Intent myaccount = new Intent(getActivity(), MyAccountActivity.class);
                startActivity(myaccount);
                //getActivity().finish();
                break;

            case R.id.txtv_logout:
                Intent Logout = new Intent(getActivity(), LoginActivity.class);
                startActivity(Logout);
                getActivity().finish();
                break;

            case R.id.txtv_login:
                Intent Login = new Intent(getActivity(), LoginActivity.class);
                startActivity(Login);
                getActivity().finish();
                break;

            case R.id.txtv_registration:
                Intent register = new Intent(getActivity(), RegistrationActivity.class);
                startActivity(register);
                getActivity().finish();
                break;
        }
    }
}
