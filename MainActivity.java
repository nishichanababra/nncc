package com.example.brijesh.datetimepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText date, time;
    Button datepicker, timepicker;

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        date = (EditText) findViewById(R.id.txtdate);
        time = (EditText) findViewById(R.id.txttime);

        datepicker = (Button) findViewById(R.id.btn_date);
        timepicker = (Button) findViewById(R.id.btn_time);

        datepicker.setOnClickListener(this);
        timepicker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_date:
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int monthofyear, int dayofmonth) {
                        date.setText(dayofmonth + "/" + (monthofyear + 1) + "/" + year1);
                    }
                }, year, month, day);
                datePickerDialog.show();


                break;
            case R.id.btn_time:


                Calendar calendar1 = Calendar.getInstance();
                int hour = calendar1.get(Calendar.HOUR);
                int minute = calendar1.get(Calendar.MINUTE);
                //int ampm=calendar1.get(Calendar.AM_PM);

                timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourofday, int minuteof) {
                        time.setText(hourofday + ":" + minuteof);
                    }
                }, hour, minute, false);
                timePickerDialog.show();
                break;
        }
    }


    //Calendar mCurrentDate;


   /* private final String TAG = "DemoNewPicker";
    private Button datepicker, timepicker;
    private TextView text;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        datepicker=(Button)findViewById(R.id.btn_date);
        //datepicker.setOnClickListener(MainActivity.this);

        text =(TextView)findViewById(R.id.textView2);

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "ondateSet : mm/dd/yyyy:" + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                text.setText(date);


            }
        };
    }



        @RequiresApi(api = Build.VERSION_CODES.N)


        public void onClick (View view){

        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int ampm = cal.get(Calendar.AM_PM);

        DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                android.R.style.Theme_DeviceDefault_Dialog, mDateSetListener,
                year,
                month,
                day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }*/

}










