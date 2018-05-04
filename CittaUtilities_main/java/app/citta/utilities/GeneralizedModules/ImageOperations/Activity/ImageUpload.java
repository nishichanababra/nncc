package app.citta.utilities.GeneralizedModules.ImageOperations.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import app.citta.utilities.utilities.Config;
import butterknife.BindView;
import butterknife.ButterKnife;
import app.citta.utilities.R;

/**
 * Created by ws-16 on 7/17/2017.
 */

public class ImageUpload extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.btn_chooseimage) Button btn_chooseImage;
    @BindView(R.id.btn_uploadImage) Button btn_UploadImage;
    @BindView(R.id.iv_image)ImageView iv_chooseImage;
    private Context context = this;
    String userChoosenTask,result, imagePath = null, deleteImagepath = "", finalImagePath = "", imageName = "", deleteImageName = "",Customerid;
    Toolbar toolbar;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    Uri targetUri = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        inittoolbar();
        ButterKnife.bind(this);
        btn_chooseImage.setOnClickListener(this);
        btn_UploadImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_chooseimage:
                chooseImage();
                break;

            case R.id.btn_uploadImage:

                break;
        }
    }

    void inittoolbar() {
        // Find the toolbar view inside the activity layout
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        ImageView back = (ImageView) toolbar.findViewById(R.id.back);
        mTitle.setText(getResources().getString(R.string.imageUpload));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    // Function to Select Image
    private void chooseImage() {

        if (finalImagePath == null || finalImagePath.isEmpty() || finalImagePath.equals("")) {
            final CharSequence[] items = {"Take New Photo", "Choose from Gallery", "Cancel"};
            AlertDialog.Builder builder = new AlertDialog.Builder(ImageUpload.this);
            builder.setTitle(" Select Image ");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    boolean resultforstorage = Config.getInstance().checkPermissionForImageUpload(ImageUpload.this, Manifest.permission.READ_EXTERNAL_STORAGE);

                    if (items[item].equals("Take New Photo")) {
                        userChoosenTask = "Take New Photo";
                        if (resultforstorage) {
                            boolean resultforcamera = Config.getInstance().checkPermissionForImageUpload(ImageUpload.this, Manifest.permission.CAMERA);
                            if (resultforcamera) {
                                cameraIntent();
                            }
                        }
                    } else if (items[item].equals("Choose from Gallery")) {
                        userChoosenTask = "Choose from Gallery";
                        if (resultforstorage) {
                            galleryIntent();
                        }
                    } else if (items[item].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }
            });
            builder.show();
        } else {
            final CharSequence[] items = {"Take New Photo", "Choose from Gallery", "Remove Photo", "Cancel"};
            AlertDialog.Builder builder = new AlertDialog.Builder(ImageUpload.this);
            builder.setTitle(" Select Image ");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    boolean resultforstorage = Config.getInstance().checkPermissionForImageUpload(ImageUpload.this, Manifest.permission.READ_EXTERNAL_STORAGE);

                    if (items[item].equals("Take New Photo")) {
                        userChoosenTask = "Take New Photo";
                        if (resultforstorage) {
                            boolean resultforcamera = Config.getInstance().checkPermissionForImageUpload(ImageUpload.this, Manifest.permission.CAMERA);
                            if (resultforcamera) {
                                cameraIntent();
                            }
                        }
                    } else if (items[item].equals("Choose from Gallery")) {
                        userChoosenTask = "Choose from Gallery";
                        if (resultforstorage) {
                            galleryIntent();
                        }
                    } else if (items[item].equals("Remove Photo")) {

                        if (deleteImagepath != null && deleteImagepath.length() > 0) {
                            int endIndex = deleteImagepath.lastIndexOf("/");
                            if (endIndex != -1) {
                                deleteImageName = deleteImagepath.substring(endIndex + 1, deleteImagepath.length());
                                deleteImagepath = Config.FTP_DIRECTORY_CUSTOMER + deleteImageName;
                            }
                        }
                        finalImagePath = null;
                        imageName = null;
                        imagePath = null;
                        Glide.with(context).load(R.mipmap.boy).asBitmap().centerCrop().into(new BitmapImageViewTarget(iv_chooseImage) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                                circularBitmapDrawable.setCircular(true);
                                iv_chooseImage.setImageDrawable(circularBitmapDrawable);
                            }
                        });
                    } else if (items[item].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }
            });
            builder.show();
        }
    }

    // Open Camera to Capture Image
    private void cameraIntent() {
        targetUri = null;

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        targetUri = getApplicationContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, targetUri);
        startActivityForResult(Intent.createChooser(cameraIntent, "Select Picture"), REQUEST_CAMERA);
    }

    // Navigate to Gallery to Choose Image
    private void galleryIntent() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, SELECT_FILE);
    }


    // Getting Results from the Activity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                onSelectFromGalleryResult(data);
            } else if (requestCode == REQUEST_CAMERA) {
                onCaptureImageResult(data);
            }
        }
    }

    private void onSelectFromGalleryResult(Intent data) {
        try {
            if (data != null) {
                Uri targetUri = data.getData();
                imagePath = "";
                finalImagePath = "";
                imageName = "";
                Cursor cursor = getContentResolver().query(targetUri, null, null, null, null);
                if (cursor == null) {
                    imagePath = targetUri.getPath();
                } else {
                    cursor.moveToFirst();
                    int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    imagePath = cursor.getString(idx);
                    cursor.close();
                }

                if (imagePath != null && imagePath.length() > 0) {
                    int endIndex = imagePath.lastIndexOf("/");
                    if (endIndex != -1) {
                        String originalStr = imagePath.substring(0, endIndex);
                        imageName = imagePath.substring(endIndex + 1, imagePath.length());
                        finalImagePath = Config.FTP_DOMAIN + Config.FTP_DIRECTORY_CUSTOMER + imageName;
                    }
                }
            }
            if ((imagePath != null)) {

                Glide.with(context).load(imagePath).asBitmap().placeholder(R.mipmap.boy).centerCrop().into(new BitmapImageViewTarget(iv_chooseImage) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        iv_chooseImage.setImageDrawable(circularBitmapDrawable);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onCaptureImageResult(Intent data) {
        try {
            Cursor cursor = null;
            imagePath = "";
            finalImagePath = "";
            try {
                String[] proj = {MediaStore.Images.Media.DATA};
                cursor = getApplicationContext().getContentResolver().query(targetUri, proj, null, null, null);
                if (cursor == null) {
                    imagePath = targetUri.getPath();
                } else {
                    cursor.moveToFirst();
                    int idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    imagePath = cursor.getString(idx);
                    cursor.close();
                }
                if (imagePath != null && imagePath.length() > 0) {
                    int endIndex = imagePath.lastIndexOf("/");
                    if (endIndex != -1) {
                        String originalStr = imagePath.substring(0, endIndex);
                        imageName = imagePath.substring(endIndex + 1, imagePath.length());
                        finalImagePath = Config.FTP_DOMAIN + Config.FTP_DIRECTORY_CUSTOMER + imageName;

                        if ((imagePath != null)) {

                            Glide.with(context).load(imagePath).asBitmap().placeholder(R.mipmap.boy).centerCrop().into(new BitmapImageViewTarget(iv_chooseImage) {
                                @Override
                                protected void setResource(Bitmap resource) {
                                    RoundedBitmapDrawable circularBitmapDrawable =
                                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                                    circularBitmapDrawable.setCircular(true);
                                    iv_chooseImage.setImageDrawable(circularBitmapDrawable);
                                }
                            });
                        }
                    }
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
