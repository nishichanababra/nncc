package com.example.demo.extraimageupload;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ImageUpload extends AppCompatActivity {

    Button btnUpload;
    private File[] listFile;
    private String[] FilePathStrings;
    private String[] FileNameStrings;
    GridView gridView;
    private ArrayList<String> images;
    private Bitmap currentImage;
    private final int CAMERA_REQUEST_PROFILE_PIC = 1;
    private final int SELECT_PICTURE_PROFILE_PIC = 2;
    File file;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    private final int requestCode = 20;
    private Uri fileUri; // file url to store image/video*/

    Button captureBtn = null;
    final int CAMERA_CAPTURE = 1;
    private Uri picUri;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private GridView grid;
    private List<String> listOfImagesPath;
    View getImages, getNImages;

    GridViewAdapter gridViewAdapter;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_CAPTURE:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();

                }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        btnUpload = findViewById(R.id.btnUplad);


        String[] PERMISSIONS = {Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridViewAdapter = new GridViewAdapter(this);
        gridview.setAdapter(gridViewAdapter);

        String ExternalStorageDirectoryPath = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath();

        String myfolder = Environment.getExternalStorageDirectory() + "/nishi" ;
        File f=new File(myfolder);
        //String targetPath = ExternalStorageDirectoryPath + "/test/";

        Toast.makeText(getApplicationContext(), myfolder, Toast.LENGTH_LONG).show();
        //File targetDirector = new File(targetPath);

        File[] files = f.listFiles();
        for (File file : files) {
            gridViewAdapter.add(file.getAbsolutePath());
        }
        gridview.setOnItemClickListener(myOnItemClickListener);


      /*  // Check for SD Card
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "Error! No SDCARD Found!", Toast.LENGTH_LONG)
                    .show();
        } else {
            // Locate the image folder in your SD Card
            file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + "SDImageTutorial");
            // Create a new folder if no folder named SDImageTutorial exist
            file.mkdirs();
        }
        if (file.isDirectory()) {
            listFile = file.listFiles();
            // Create a String array for FilePathStrings
            FilePathStrings = new String[listFile.length];
            // Create a String array for FileNameStrings
            FileNameStrings = new String[listFile.length];

            for (int i = 0; i < listFile.length; i++) {
                // Get the path of the image file
                FilePathStrings[i] = listFile[i].getAbsolutePath();
                // Get the name image file
                FileNameStrings[i] = listFile[i].getName();
            }
        }
*/

        ActivityCompat.requestPermissions(this, PERMISSIONS, 200);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCameraDialog();
                /*Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoCaptureIntent, requestCode);*/
            }
        });
    }

    AdapterView.OnItemClickListener myOnItemClickListener
            = new AdapterView.OnItemClickListener() {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String prompt = (String)parent.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(),
                    prompt,
                    Toast.LENGTH_LONG).show();
        }
    };

        @Override
    protected void onStart() {
        super.onStart();
        createFolder("nishi");
    }
    public void createFolder(String fname) {
        String myfolder = Environment.getExternalStorageDirectory() + "/" + fname;
        File f=new File(myfolder);



        if(!f.exists())
            if(!f.mkdir()){
                Toast.makeText(this, myfolder+" can't be created.", Toast.LENGTH_SHORT).show();

            }
            else
                Toast.makeText(this, myfolder+" can be created.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, myfolder+" already exits.", Toast.LENGTH_SHORT).show();


        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fnm = "Image-"+ n +".jpg";
        File file = new File (f, fnm);
        if (file.exists ())
            file.delete ();
        try {

            FileOutputStream out=new FileOutputStream(file);
          //  ByteArrayOutputStream out = new ByteArrayOutputStream();
            Bitmap bitmap=BitmapFactory.decodeFile(fnm);

           // boolean compress=bitmap.compress(Bitmap.CompressFormat.PNG,90,out);
            currentImage.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

           /* Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));

            Log.e("Original   dimensions", currentImage.getWidth()+" "+currentImage.getHeight());
            Log.e("Compressed dimensions", decoded.getWidth()+" "+decoded.getHeight());*/

        } catch (Exception e) {
            e.printStackTrace();
        }

       /* FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
            bitMap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    bitMap, myPath.getPath(), fileName);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }


    /**
     * This method will open camera and gallery
     */
    private void openCameraDialog() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(ImageUpload.this);

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


}



