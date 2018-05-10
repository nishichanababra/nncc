package com.example.demo.extraimageupload;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImageUpload extends AppCompatActivity {

    final int CAMERA_CAPTURE = 1;
    private final int CAMERA_REQUEST_PROFILE_PIC = 1;
    private final int SELECT_PICTURE_PROFILE_PIC = 2;
    private final int requestCode = 20;
    Button btnUpload;
    GridView gridView;
    File file;
    Button captureBtn = null;
    View getImages, getNImages;
    GridViewAdapter gridViewAdapter;
    AdapterView.OnItemClickListener myOnItemClickListener
            = new AdapterView.OnItemClickListener() {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String prompt = (String) parent.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(),
                    prompt,
                    Toast.LENGTH_LONG).show();
           /* Intent intent=new Intent(ImageUpload.this,DisplayActivity.class);
            intent.putExtra(""+listOfImagesPath);
            startActivity(intent);*/
        }
    };
    private File[] listFile;
    private String[] FilePathStrings;
    private String[] FileNameStrings;
    private ArrayList<String> images;
    private Bitmap currentImage;
    private Uri fileUri; // file url to store image/video*/
    private Uri picUri;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private GridView grid;
    private List<String> listOfImagesPath;
    private Camera camera;
    protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_CAPTURE:
                if (resultCode == RESULT_OK) {
                    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
                        Uri selectedImage = data.getData();
                        Bundle extras = data.getExtras();

                        Log.e("URI", selectedImage.toString());

                        Bitmap bmp = (Bitmap) extras.get("data");
                    }
                    else if (resultCode == RESULT_CANCELED) {
                        Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT);
                    }
                }    /*  ClipData pickedImage = data.getClipData();
                    // Let's read picked image path using content resolver
                    String[] filePath = { MediaStore.Images.Media.DATA };
                    int maxImageCount = pickedImage.getItemCount() > 4 ? 4 : pickedImage.getItemCount();
                    for(int i = 0; i < maxImageCount; i ++) {
                        addImageToList(pickedImage.getItemAt(i).getUri());
                    }

                   // gridViewAdapter.setImages(images);
                    gridViewAdapter.notifyDataSetChanged();
                }
                else if (requestCode == CAMERA_REQUEST_PROFILE_PIC) {
                    if (resultCode == RESULT_OK && picUri != null) {
                        images.add(picUri.getPath());
                       // gridViewAdapter.setImages(images);
                        gridViewAdapter.notifyDataSetChanged();
                    } else if (resultCode == RESULT_CANCELED) {
                    } else {
                        // Image capture failed, advise user
                    }*/

        }
    }

    private void addImageToList(Uri uri) {
        String[] filePath = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, filePath, null, null, null);
        cursor.moveToFirst();
        String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
        images.add(imagePath);
        cursor.close();
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

        String myfolder = Environment.getExternalStorageDirectory() + "/nishi";
        File f = new File(myfolder);
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

    @Override
    protected void onStart() {
        super.onStart();
        createFolder("nishi");
    }

    public void createFolder(String fname) {

        String myfolder = Environment.getExternalStorageDirectory() + "/" + fname;
        File f = new File(myfolder);

        if (!f.exists())
            if (!f.mkdir()) {
                Toast.makeText(this, myfolder + " can't be created.", Toast.LENGTH_SHORT).show();

            } else
                Toast.makeText(this, myfolder + " can be created.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, myfolder + " already exits.", Toast.LENGTH_SHORT).show();

        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fnm = "Image-" + n + ".jpg";
        File file = new File(f, fnm);
        if (file.exists())
            file.delete();
        try {

            FileOutputStream out = new FileOutputStream(file);
            //  ByteArrayOutputStream out = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeFile(fnm);

            // boolean compress=bitmap.compress(Bitmap.CompressFormat.PNG,90,out);
            currentImage.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();



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



