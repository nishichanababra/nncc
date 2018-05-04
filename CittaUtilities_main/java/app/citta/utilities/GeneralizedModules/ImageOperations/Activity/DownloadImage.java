package app.citta.utilities.GeneralizedModules.ImageOperations.Activity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import app.citta.utilities.R;
import app.citta.utilities.utilities.Config;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DownloadImage extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.iv1) ImageView img1;
    @BindView(R.id.iv2) ImageView img2;
    @BindView(R.id.iv3) ImageView img3;
    @BindView(R.id.btn1)Button btn1;
    @BindView(R.id.btn2)Button btn2;
    @BindView(R.id.btn3)Button btn3;
    public final String imgUrl1 = "http://mygps.demoforyou.co.in/ImageFolder/RefillerImage/IMG-20170628-WA0005.jpg";
    public final String imgUrl2 = "http://mygps.demoforyou.co.in/ImageFolder/CustomerImage/20170709_085256.jpg";
    public final String imgUrl3 = "http://mygps.demoforyou.co.in/ImageFolder/RefillerImage/PicsArt_1430077216079.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_image);
        ButterKnife.bind(this);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        inittoolbar();

        Glide.with(DownloadImage.this).load(imgUrl1).asBitmap().placeholder(R.mipmap.boyicon).centerCrop().into(new BitmapImageViewTarget(img1) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                img1.setImageDrawable(circularBitmapDrawable);
            }
        });

        Glide.with(DownloadImage.this).load(imgUrl2).asBitmap().placeholder(R.mipmap.boyicon).centerCrop().into(new BitmapImageViewTarget(img2) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                img2.setImageDrawable(circularBitmapDrawable);
            }
        });

        Glide.with(DownloadImage.this).load(imgUrl3).asBitmap().placeholder(R.mipmap.boyicon).centerCrop().into(new BitmapImageViewTarget(img3) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                img3.setImageDrawable(circularBitmapDrawable);
            }
        });
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                new DownloadFileFromURL().execute(imgUrl1);
                break;

            case R.id.btn2:
                new DownloadFileFromURL().execute(imgUrl2);
                break;

            case R.id.btn3:
                new DownloadFileFromURL().execute(imgUrl3);
                break;
        }
    }


    public class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Config.getInstance().startProgressdialog(DownloadImage.this,Config.PLEASE_WAIT);
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                String root = Environment.getExternalStorageDirectory().toString();

                System.out.println("Downloading");
                URL url = new URL(f_url[0]);

                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file

                OutputStream output = new FileOutputStream(root+"/image1.jpg");
                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;

                    // writing data to file
                    output.write(data, 0, count);
                }
                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            return null;
        }

        /**
         * After completing background task
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            Config.getInstance().stopProgressdialog();
            Config.getInstance().ToastWithMsg(DownloadImage.this,"Image 1 Downloaded successfully !!");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
