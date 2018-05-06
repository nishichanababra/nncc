package com.css.opddoctor.activity;

/*
 * Created by Jay-Raj on 2/2/2018.
 */

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.css.opddoctor.R;
import com.css.opddoctor.adapter.ImageListingAdapter;
import com.css.opddoctor.model.ImageListingModel;
import com.css.opddoctor.utils.ImageUtils;
import com.css.opddoctor.webservice.WebServiceHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ImageListingAcitivity extends ParentActivity {

    private String TAG = "ImageListingAcitivity";
    private Button btnDone;
    private Button btnAddNewPhoto;
    private RecyclerView DieaseAlbumRecycler;
    private ArrayList<ImageListingModel> arrayList = new ArrayList<ImageListingModel>();
    private GridLayoutManager gridLayoutManager;

    private RecyclerView.Adapter adapter;
    private Bitmap currentImage;

    private final int CAMERA_REQUEST_PROFILE_PIC = 1;
    private final int SELECT_PICTURE_PROFILE_PIC = 2;
    private String binaryImage = "";
    private String albumId = "0";
    private int addImagePosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_listing);

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

        setAdapter();

        getIntentData();

    }

    void initUIControls() {

        btnDone = findViewById(R.id.btnDone);
        imgTopLeft = findViewById(R.id.imgTopLeft);
        btnAddNewPhoto = findViewById(R.id.btnAddNewPhoto);
        DieaseAlbumRecycler = findViewById(R.id.DieaseAlbumRecycler);

    }

    @Override
    protected void initTopBarComponents() {
        super.initTopBarComponents();
        imgTopLeft.setImageDrawable(getResources().getDrawable(R.drawable.top_back));
    }

    @Override
    void registerForListener() {
        super.registerForListener();
        btnDone.setOnClickListener(this);
        imgTopLeft.setOnClickListener(this);
        btnAddNewPhoto.setOnClickListener(this);
    }


    @Override
    void setAdapter() {
        super.setAdapter();
        gridLayoutManager = new GridLayoutManager(ImageListingAcitivity.this, 4);
        /*for (int j = 0; j < 10; j++) {

            ImageListingModel addDieseaseImageModel = new ImageListingModel();
            arrayList.add(addDieseaseImageModel);
        }*/

        adapter = new ImageListingAdapter(this, arrayList);
        DieaseAlbumRecycler.setLayoutManager(gridLayoutManager);
        DieaseAlbumRecycler.setAdapter(adapter);
        DieaseAlbumRecycler.setHasFixedSize(true);

    }

    @Override
    void getIntentData() {
        Bundle extras = getIntent().getExtras();
        String value = null;
        if (extras != null) {
            value = extras.getString("album_name");
            albumId = extras.getString("album_id");
            Log.d(TAG, "getIntentData: " + albumId);
        }
        txtTopCenter.setText(value);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.imgTopLeft:
                navigateToActivity(AlbumListingActivity.class, true);
                break;

            case R.id.btnDone:
                callUploadImageWebservice();
                break;

            case R.id.btnAddNewPhoto:
                openCameraDialog();
                break;

        }
    }

    /***
     * method will call upload image webservice
     */
    private void callUploadImageWebservice() {
        WebServiceHelper webServiceHelper = new WebServiceHelper(this);
        for (int i = 0; i < arrayList.size(); i++) {


            String code_url = getResources().getString(R.string.CODE_URL);
            String API = "add_album_image.php";
            String POST_URL = code_url + API;

            HashMap<String, String> params = new HashMap<>();
            params.put("action", "image_upload");
            params.put("binary_data", arrayList.get(i).getPath());


            params.put("album_id", albumId);

            final int finalI = i;
            webServiceHelper.callWS(POST_URL, params, new WebServiceHelper.JSONRequestHandlerPost() {
                @Override
                public void onSuccess(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject object = jsonObject.optJSONObject("header");
                        Log.d(TAG, "status :: " + object.optString("status"));
                        Log.d(TAG, "message :: " + object.optString("message"));

                        switch (object.optString("status")) {
                            case "200":
                                if (arrayList.size() > 0) {
                                    if (finalI == arrayList.size() - 1) {
                                        navigateToActivity(AlbumListingActivity.class, true);
                                    }
                                }
                                break;

                            case "701":
                                showToast(WebServiceHelper.MESSAGE + "701");
                                break;

                            case "704":
                                showToast(WebServiceHelper.MESSAGE + "704");
                                break;

                            case "709":
                                showToast(WebServiceHelper.MESSAGE + "709");
                                break;

                            case "778":
                                showToast(WebServiceHelper.MESSAGE + "778");
                                break;

                            case "725":
                                showToast(WebServiceHelper.MESSAGE + "725");
                                break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } finally {

                    }
                    //Toast.makeText(LoginActivity.this, "successfully", Toast.LENGTH_LONG).show();
                }

         /*   @Override
            public void onFailure(VolleyError error) {
                super.onFailure(error);
            }*/

                public void networkNotAvailable(boolean isNetworkAvailable) {
                    if (!isNetworkAvailable) {
                        Toast.makeText(ImageListingAcitivity.this, "user network connection closed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }   //end of callUploadImageWebservice

    /**
     * This method will open camera and gallery
     */
    private void openCameraDialog() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(ImageListingAcitivity.this);

        builder.setTitle("Add Photo!");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(intent, CAMERA_REQUEST_PROFILE_PIC);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

                    photoPickerIntent.setType("image/*");

                    startActivityForResult(photoPickerIntent, SELECT_PICTURE_PROFILE_PIC);

                } else if (options[item].equals("Cancel")) {

                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            Log.d(TAG, "onActivityResult :: data :: " + data);

            if (data != null) {
                if (requestCode == CAMERA_REQUEST_PROFILE_PIC) {//profile from camera
                    browseCameraImage(data);
                } else if (requestCode == SELECT_PICTURE_PROFILE_PIC) {//profile from gallery
                    uploadPicFromGallery(data, requestCode);
                }
            }
        }
    }

    //camera request handle
    private void browseCameraImage(Intent data) {
        if (data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            String profilePicBinaryData = binaryImage = ImageUtils.encodeToBase64(bitmap);

            binaryImage = profilePicBinaryData;
            ImageListingModel imageListingModel = new ImageListingModel();
            imageListingModel.setImgType("camera");
            imageListingModel.setPath(binaryImage);
            arrayList.add(imageListingModel);
            //storeBinaryData(binaryImage);
            Log.d(TAG, "uploadPicFromCamera: " + binaryImage);
            adapter.notifyDataSetChanged();

        }
    }//end of browseCameraImage()

    //handle gallery request
    private void uploadPicFromGallery(Intent data, int requestCode) {
        Uri uri = data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            //  ByteArrayOutputStream out = new ByteArrayOutputStream();
            //  bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);  //used to compress uploaded image
            binaryImage = ImageUtils.encodeToBase64(bitmap);
            Log.d(TAG, "uploadPicFromGallery: " + binaryImage);
            // storeBinaryData(binaryImage);
            ImageListingModel imageListingModel = new ImageListingModel();
            imageListingModel.setImgType("gallery");
            imageListingModel.setPath(binaryImage);
            arrayList.add(imageListingModel);
            //storeBinaryData(binaryImage);
            Log.d(TAG, "uploadPicFromgallery: " + binaryImage);
            adapter.notifyDataSetChanged();

        } catch (IOException e) {
            showToast("Error in uploading your image please upload again ...!");
            e.printStackTrace();
        }

    }//end of uploadPicFromGallery()

    /***
     * will store binary data
     * @param binaryImage
     */
    private void storeBinaryData(String binaryImage) {
        ImageListingModel imageListingModel = arrayList.get(addImagePosition);

        imageListingModel.setPath(binaryImage);

        adapter.notifyDataSetChanged();
    }   //end of storeBinaryData

}//end of ImageListingAcitivity