package com.css.opdpatient.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.css.opdpatient.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class PersonalInformationActivity extends ParentActivity {
    private String TAG = "PersonalInformationActivity";
    private TextView txtMale, txtFemale, txtDOB, txtMarried, txtUnMarried, txtCountry, txtState, txtCity;
    private EditText edtFirstnm, edtLastnm, edtEmail, edtMobile, edtZipcode, edtAddress;
    private Button btnUpdateProfile;
    private String gender = "male";
    private String marital = "Married";
    private DatePickerDialog datePickerDialog;
    private ImageView imgAddImgProfile,imgAddImg;
    private Bitmap currentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        String[] PERMISSIONS = {Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        ActivityCompat.requestPermissions(this, PERMISSIONS, 200);

        initUIControls();

        initTopBarComponents();

        registerForListener();

        setUIData();
    }

    @Override
    void initUIControls() {
        super.initUIControls();
        imgTopLeft = findViewById(R.id.imgTopLeft);
        txtMale = findViewById(R.id.txtMale);
        txtFemale = findViewById(R.id.txtFemale);
        txtDOB = findViewById(R.id.txtDOB);
        txtMarried = findViewById(R.id.txtMarried);
        txtUnMarried = findViewById(R.id.txtUnMarried);
        txtCountry = findViewById(R.id.txtCountry);
        txtState = findViewById(R.id.txtState);
        txtCity = findViewById(R.id.txtCity);
        edtFirstnm = findViewById(R.id.edtFirstnm);
        edtLastnm = findViewById(R.id.edtLastnm);
        edtEmail = findViewById(R.id.edtEmail);
        edtAddress = findViewById(R.id.edtAddress);
        edtMobile = findViewById(R.id.edtMobile);
        edtZipcode = findViewById(R.id.edtZipcode);
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile);
        imgAddImgProfile = findViewById(R.id.imgAddImgProfile);
        imgAddImg = findViewById(R.id.imgAddImg);
    }

    @Override
    protected void initTopBarComponents() {
        super.initTopBarComponents();
        imgTopLeft.setImageDrawable(getResources().getDrawable(R.drawable.top_back));
    }

    @Override
    void setUIData() {
        super.setUIData();
        txtTopCenter.setText(getResources().getString(R.string.top_EditAcount));
       // Picasso.with(this).load("https://randomuser.me/api/portraits/men/9.jpg").into(imgAddImg);
    }


    @Override
    void registerForListener() {
        super.registerForListener();
        imgTopLeft.setOnClickListener(this);
        txtMale.setOnClickListener(this);
        txtFemale.setOnClickListener(this);
        txtDOB.setOnClickListener(this);
        txtMarried.setOnClickListener(this);
        txtUnMarried.setOnClickListener(this);
        txtCountry.setOnClickListener(this);
        txtState.setOnClickListener(this);
        txtCity.setOnClickListener(this);
        btnUpdateProfile.setOnClickListener(this);
        imgAddImgProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.imgTopLeft:
                navigateToActivity(MyAccountActivity.class, false);
                break;

            case R.id.txtMale:
                txtMale.setBackgroundColor(getResources().getColor(R.color.white));
                txtMale.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                txtFemale.setBackgroundColor(getResources().getColor(R.color.white));
                txtFemale.setTextColor(getResources().getColor(R.color.grey));
                gender = "male";
                break;

            case R.id.txtFemale:
                txtFemale.setBackgroundColor(getResources().getColor(R.color.white));
                txtFemale.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                txtMale.setBackgroundColor(getResources().getColor(R.color.white));
                txtMale.setTextColor(getResources().getColor(R.color.grey));
                gender = "famale";
                break;

            case R.id.txtDOB:
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(PersonalInformationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int monthofyear, int dayofmonth) {
                        txtDOB.setText(dayofmonth + "/" + (monthofyear + 1) + "/" + year1);
                        txtDOB.setText(year1 + " / " + (monthofyear + 1) + " / " + dayofmonth);
                    }
                }, year, month, day);
                datePickerDialog.show();

                break;

            case R.id.txtMarried:
                txtMarried.setBackgroundColor(getResources().getColor(R.color.white));
                txtMarried.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                txtUnMarried.setBackgroundColor(getResources().getColor(R.color.white));
                txtUnMarried.setTextColor(getResources().getColor(R.color.grey));
                marital = "Married";
                break;

            case R.id.txtUnMarried:
                txtMarried.setBackgroundColor(getResources().getColor(R.color.white));
                txtMarried.setTextColor(getResources().getColor(R.color.grey));
                txtUnMarried.setBackgroundColor(getResources().getColor(R.color.white));
                txtUnMarried.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                marital = "Unmarried";
                break;

            case R.id.txtCountry:
                break;

            case R.id.txtState:
                break;

            case R.id.txtCity:
                break;

            case R.id.imgAddImgProfile:
                selectImage();

                break;

            case R.id.btnUpdateProfile:
                if (edtFirstnm.getText().toString().isEmpty()) {

                    edtFirstnm.setError("First Name required");

                } else if (edtLastnm.getText().toString().isEmpty()) {

                    edtLastnm.setError("Last Name required");

                } else if (edtEmail.getText().toString().isEmpty()) {

                    edtEmail.setError("Email is reduired");

                } else if (edtAddress.getText().toString().isEmpty()) {

                    edtAddress.setError("Address is reduired");

                } else if (edtMobile.getText().toString().length() < 10) {

                    edtMobile.setError("Mobile number shoud not less than 10");

                } else if (edtZipcode.getText().toString().isEmpty()) {

                    edtZipcode.setError("Zipcode is reduired");

                } else if (gender == null) {

                    Toast.makeText(this, "Choose your gender", Toast.LENGTH_SHORT).show();

                } else if (marital == null) {

                    Toast.makeText(this, "Choose you are Married or Unmarried", Toast.LENGTH_SHORT).show();

                } else {
                    navigateToActivity(MyAccountActivity.class, false);
                }

                break;
        }
    }

    /**
     * This method allow us to take images from camera and gallery
     */
    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(PersonalInformationActivity.this);

        builder.setTitle("Add Photo!");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo"))

                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(intent, 1);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, 2);
                } else if (options[item].equals("Cancel")) {

                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }//end of selectImage


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {

                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imgAddImg.setImageBitmap(photo);

            } else if (requestCode == 2) {

                Uri photoUri = data.getData();
                if (photoUri != null) {
                    try {
                        currentImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
                        imgAddImg.setImageBitmap(currentImage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }//end of onActivityResult

    public void onStop() {
        super.onStop();
        if (currentImage != null) {
            currentImage.recycle();
            currentImage = null;
            System.gc();
        }
    }//end of onStop
}//end of PersonalInformationActivity
