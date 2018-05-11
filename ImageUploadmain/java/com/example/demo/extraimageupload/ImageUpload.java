package com.example.demo.extraimageupload;

import android.Manifest;
import android.animation.Animator;
import android.content.Context;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private Context mcontext;
    private ImageView expandedImageView;
    String name;
    private Animator mCurrentAnimator;
    int j=0;
    private ImageView imageview;


    public byte[] mybytearray  = new byte[310000];
    private int bytesRead=0;
    private int current = 0;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_CAPTURE:
                if (resultCode == RESULT_OK) {
                    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {

                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");

                        String partFilename = currentDateFormat();
                        storeCameraPhotoInSDCard(bitmap, partFilename);

                        // display the image from SD Card to ImageView Control
                        String storeFilename = "photo_" + partFilename + ".jpg";
                        Bitmap mBitmap = getImageFileFromSDCard(storeFilename);
                        imageview.setImageBitmap(mBitmap);

                        createFolder(name);
                    }
                        /*Uri selectedImage = data.getData();
                        Bundle extras = data.getExtras();

                        Log.e("URI", selectedImage.toString());
                        //ByteArrayOutputStream bout=new ByteArrayOutputStream();
                        Bitmap bmp = (Bitmap) extras.get("data");
                        //bmp.compress(Bitmap.CompressFormat.JPEG,90,bout);
                        addImageToList(picUri);
                        createFolder(name);
                    }
                    else if (resultCode == RESULT_CANCELED) {
                        Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT);
                    }
                }*/ /*     ClipData pickedImage = data.getClipData();
                    // Let's read picked image path using content resolver
                    String[] filePath = { MediaStore.Images.Media.DATA };
                    int maxImageCount = pickedImage.getItemCount() > 4 ? 4 : pickedImage.getItemCount();
                    for(int i = 0; i < maxImageCount; i ++) {
                        addImageToList(pickedImage.getItemAt(i).getUri());
                    }

                   // gridViewAdapter.setImages(images);
                    gridViewAdapter.notifyDataSetChanged();*/

               /* else if (requestCode == CAMERA_REQUEST_PROFILE_PIC) {
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
        imageview=findViewById(R.id.imageview);

       // gridViewAdapter = new GridViewAdapter(this);
        //gridview.setAdapter(gridViewAdapter);

        String ExternalStorageDirectoryPath = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath();

        String myfolder = Environment.getExternalStorageDirectory() + "/nishi";
        File f = new File(myfolder);
        //String targetPath = ExternalStorageDirectoryPath + "/test/";

        Toast.makeText(getApplicationContext(), myfolder, Toast.LENGTH_LONG).show();
        //File targetDirector = new File(targetPath);


        gridview.setOnItemClickListener(myOnItemClickListener);
        gridview.setAdapter(gridViewAdapter);





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
           // Bitmap bitmap = BitmapFactory.decodeFile(fnm);
            Bitmap mbitmap=getImageFileFromSDCard(myfolder);
           // imageview.setImageBitmap(mbitmap);
            // boolean compress=bitmap.compress(Bitmap.CompressFormat.PNG,90,out);
            mbitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Intent galleryIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File f1 = new File(myfolder);
            Uri picUri = Uri.fromFile(f1);
            galleryIntent.setData(picUri);
            this.sendBroadcast(galleryIntent);



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
    private Bitmap getImageFileFromSDCard(String filename){
        Bitmap bitmap = null;
        File imageFile = new File(Environment.getExternalStorageDirectory() + filename);
        try {
            FileInputStream fis = new FileInputStream(imageFile);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private void storeCameraPhotoInSDCard(Bitmap bitmap, String currentDate){
        File outputFile = new File(Environment.getExternalStorageDirectory(), "photo_" + currentDate + ".jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String currentDateFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String  currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
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





   /* public class GridViewAdapter extends BaseAdapter {


        private LayoutInflater inflater = null;
        ArrayList<String> images = new ArrayList<String>();
        private Activity activity;
        private Context mContext;
        private List<String> data = new ArrayList<String>();
        private String[] filepath;
        private String[] filename;
        private Activity context;
        private ArrayList arrayList = new ArrayList();

        private Animator mCurrentAnimator;
        private int mShortAnimationDuration;
        private int j = 0;


        public GridViewAdapter(Context mContext) {
            this.mContext = mContext;
        }

        *//*  public GridViewAdapter(Activity activity) {
                this.activity = activity;
                images = getAllShownImagesPath(context);

            }*//*
        void add(String path) {
            images.add(path);
        }


        @Override
        public int getCount() {
            return images.size();
            //return filepath.length;

        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;

            ImageView imageView;

            if (convertView == null) {  // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(220, 220));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            Bitmap bm = decodeSampledBitmapFromUri(images.get(position), 220, 220);

            imageView.setImageBitmap(bm);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = (Integer) v.getTag();
                    zoomImageFromThumb(v, id);
                }
            });
            return imageView;
       *//* picturesView = convertView.findViewById(R.id.imageview);
        if (convertView == null) {
            picturesView = new ImageView(context);
            picturesView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            picturesView
                    .setLayoutParams(new GridView.LayoutParams(270, 270));

        } else {
            picturesView = (ImageView) convertView;
        }

        Glide.with(context).load(images.get(position))
                .into(picturesView);*//*

        }

      public void zoomImageFromThumb(final View thumbView, int imageResId) {
            // If there's an animation in progress, cancel it immediately and
            // proceed with this one.
            if (mCurrentAnimator != null) {
                mCurrentAnimator.cancel();
            }

            // Load the high-resolution "zoomed-in" image.
            final ImageView expandedImageView = (ImageView) ((Activity) mContext)
                    .findViewById(R.id.imageview);
            expandedImageView.setImageResource(imageResId);

            // Calculate the starting and ending bounds for the zoomed-in image.
            // This step
            // involves lots of math. Yay, math.
            final Rect startBounds = new Rect();
            final Rect finalBounds = new Rect();
            final Point globalOffset = new Point();

            thumbView.getGlobalVisibleRect(startBounds);
            ((Activity) mContext).findViewById(R.id.imageview)
                    .getGlobalVisibleRect(finalBounds, globalOffset);
            startBounds.offset(-globalOffset.x, -globalOffset.y);
            finalBounds.offset(-globalOffset.x, -globalOffset.y);

            float startScale;
            if ((float) finalBounds.width() / finalBounds.height() > (float) startBounds
                    .width() / startBounds.height()) {
                // Extend start bounds horizontally
                startScale = (float) startBounds.height() / finalBounds.height();
                float startWidth = startScale * finalBounds.width();
                float deltaWidth = (startWidth - startBounds.width()) / 2;
                startBounds.left -= deltaWidth;
                startBounds.right += deltaWidth;
            } else {
                // Extend start bounds vertically
                startScale = (float) startBounds.width() / finalBounds.width();
                float startHeight = startScale * finalBounds.height();
                float deltaHeight = (startHeight - startBounds.height()) / 2;
                startBounds.top -= deltaHeight;
                startBounds.bottom += deltaHeight;

                thumbView.setAlpha(0f);
                expandedImageView.setVisibility(View.VISIBLE);

                // Set the pivot point for SCALE_X and SCALE_Y transformations to the
                // top-left corner of
                // the zoomed-in view (the default is the center of the view).
                expandedImageView.setPivotX(0f);
                expandedImageView.setPivotY(0f);
            }
            AnimatorSet set = new AnimatorSet();
            set.play(
                    ObjectAnimator.ofFloat(expandedImageView, View.X,
                            startBounds.left, finalBounds.left))
                    .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                            startBounds.top, finalBounds.top))
                    .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                            startScale, 1f))
                    .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y,
                            startScale, 1f));
            set.setDuration(mShortAnimationDuration);
            set.setInterpolator(new DecelerateInterpolator());
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationCancel(Animator animation) {
                    mCurrentAnimator = null;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mCurrentAnimator = null;
                }
            });
            set.start();
            mCurrentAnimator = set;

            // Upon clicking the zoomed-in image, it should zoom back down to the
            // original bounds
            // and show the thumbnail instead of the expanded image.
            final float startScaleFinal = startScale;
            expandedImageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mCurrentAnimator != null) {
                        mCurrentAnimator.cancel();
                    }
                }
            });
        }


        public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {

            Bitmap bm = null;
            // First decode with inJustDecodeBounds=true to check dimensions
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            bm = BitmapFactory.decodeFile(path, options);

            return bm;
        }

        public int calculateInSampleSize(

                BitmapFactory.Options options, int reqWidth, int reqHeight) {
            // Raw height and width of image
            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            if (height > reqHeight || width > reqWidth) {
                if (width > height) {
                    inSampleSize = Math.round((float) height / (float) reqHeight);
                } else {
                    inSampleSize = Math.round((float) width / (float) reqWidth);
                }
            }

            return inSampleSize;
        }



      *//*  return picturesView;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(position, parent, false);
            holder = new ViewHolder();

            holder.image = (ImageView) row.findViewById(R.id.imageview);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        ImageItem item = arrayList.get(position);
        holder.image.setImageBitmap(item.getImage());
        return row;
*//*



    *//*public  class ViewHolder {
        ImageView image;
    }*//*


        public class ImageItem {
            private Bitmap image;

            public Bitmap getImage() {
                return image;
            }

            public void setImage(Bitmap image) {
                this.image = image;
            }
        }
    }



*/





}






