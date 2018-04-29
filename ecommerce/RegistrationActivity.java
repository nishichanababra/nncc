package com.company.ecommerce;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class RegistrationActivity extends ParentActivity {

    private String TAG = "RegistrationActivity ";
    private EditText edtfirstname, edtlastname, edtEmail, edtMobileno, edtDob;
    private DrawerLayout DrawerLayout;
    private DatePickerDialog datePickerDialog;
    private Button btnRegister;
    private ImageView img_addtocart;
    private View fragment;
    private TextView txtLoginNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        initUIControls();
        initTopBarComponents();
        registerForListener();
        setUIData();

    }


    void initUIControls() {
        edtDob = findViewById(R.id.edtDob);
        btnRegister = findViewById(R.id.btnRegister);
        imgTopLeft = findViewById(R.id.imgTopLeft);
        img_addtocart = findViewById(R.id.img_addtocart);
        DrawerLayout = findViewById(R.id.DrawerLayout);
        fragment = findViewById(R.id.fragment);
        txtLoginNow = findViewById(R.id.txtLoginNow);

    }

    @Override
    protected void initTopBarComponents() {
        super.initTopBarComponents();
        img_addtocart.setVisibility(View.VISIBLE);
    }

    void registerForListener() {
        edtDob.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        img_addtocart.setOnClickListener(this);
        txtLoginNow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edtDob:
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(RegistrationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int monthofyear, int dayofmonth) {
                        edtDob.setText(dayofmonth + "/" + (monthofyear + 1) + "/" + year1);
                    }
                }, year, month, day);
                datePickerDialog.show();
                break;

            case R.id.btnRegister:

             /*   if (edtfirstname.getText().toString().isEmpty()) {
                    edtfirstname.setError("firstname required");
                } else if (edtlastname.getText().toString().isEmpty()) {
                    edtlastname.setError("lastname required");
                } else if (edtEmail.getText().toString().isEmpty()) {
                    edtEmail.setError("email address required");
                } else {*/
                    navigateToActivity(HomePageActivity.class, false);

                break;


            case R.id.imgTopLeft:
                openDrawer(DrawerLayout, fragment);
                break;


            case R.id.txtLoginNow:
                navigateToActivity(LoginActivity.class, false);
                break;


        }
    }
}
