package com.app.citta.sukhnidayri.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SearchView;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.citta.sukhnidayri.R;
import com.app.citta.sukhnidayri.adapter.DBAdapter;
import com.app.citta.sukhnidayri.utils.ScreenshotUtils;
import com.github.barteksc.pdfviewer.PDFView;

import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

import java.io.File;


public class Mybook extends PDFViewActivity implements HasViews, OnViewChangedListener {

    private final static int REQUEST_CODE = 42;
    private final Context c = this;
    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    RelativeLayout relativeLayout;
    private String gopageno, bookmarkname;
    private DBAdapter dbAdapter;
    private EditText edpageno, ebookmark;
    private Handler handler;
    final int PIC_CROP = 2;

    public static IntentBuilder_ intent(Context context) {
        return new IntentBuilder_(context);
    }

    public static IntentBuilder_ intent(Fragment fragment) {
        return new IntentBuilder_(fragment);
    }

    public static IntentBuilder_ intent(android.support.v4.app.Fragment supportFragment) {
        return new IntentBuilder_(supportFragment);
    }

    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(R.layout.activity_main);
        dbAdapter = new DBAdapter(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        relativeLayout = (RelativeLayout) findViewById(R.id.root_content);
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        NonConfigurationInstancesHolder nonConfigurationInstance = ((NonConfigurationInstancesHolder) super.getLastCustomNonConfigurationInstance());
        if (nonConfigurationInstance != null) {
            uri = nonConfigurationInstance.uri;
            pageNumber = nonConfigurationInstance.pageNumber;
        }
    }

    //.........set layout of page..............
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    //........call when page view is changed...........

    @Override
    public void onViewChanged(HasViews hasViews) {
        this.pdfView = ((PDFView) hasViews.findViewById(R.id.pdfView));
        afterViews();
    }

    //.......get last stored page .......

    @Override
    public Object getLastCustomNonConfigurationInstance() {
        NonConfigurationInstancesHolder nonConfigurationInstance = ((NonConfigurationInstancesHolder) super.getLastCustomNonConfigurationInstance());
        if (nonConfigurationInstance == null) {
            return null;
        }
        return nonConfigurationInstance.superNonConfigurationInstance;
    }

    @Override
    public NonConfigurationInstancesHolder onRetainCustomNonConfigurationInstance() {
        NonConfigurationInstancesHolder nonConfigurationInstanceState_ = new NonConfigurationInstancesHolder();
        nonConfigurationInstanceState_.superNonConfigurationInstance = super.onRetainCustomNonConfigurationInstance();
        nonConfigurationInstanceState_.uri = uri;
        nonConfigurationInstanceState_.pageNumber = pageNumber;
        return nonConfigurationInstanceState_;
    }

    //......cretae actionbar ....................


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.options, menu);

        // search word

        MenuItem item = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    // pick file from storage
    void pickFile() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE}, PERMISSION_CODE);

            return;
        }
        launchPicker();
    }

    void launchPicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            //alert user that file manager not working
            Toast.makeText(this, R.string.toast_pick_file_error, Toast.LENGTH_SHORT).show();
        }
    }

    //..........set itemclick Listener .............

    @RequiresApi(api = VERSION_CODES.ICE_CREAM_SANDWICH)
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.bookmark:
                bookmark();
                break;

            case R.id.pickFile:
                pickFile();
                break;

            case R.id.open:
                Intent in3 = new Intent(Mybook.this, OpenBookMark.class);
                startActivityForResult(in3, 505);
                break;

            case R.id.go:
                gotoPage();
                break;

/*
            case R.id.day_night:
                UiModeManager uiManager = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);
                uiManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
                handler=new Handler();
                pdfView.setBackgroundColor(getResources().getColor(R.color.colorAppBackground));
                int themeMode = AppCompatDelegate.getDefaultNightMode();
                if(themeMode == AppCompatDelegate.MODE_NIGHT_YES){
                    handler = new Handler();
                    relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorAppBackground));

                }
                else {
                    handler = new Handler();
                    relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorAppBackground));
                }
                break;*/
            case R.id.screenshot:
                Bitmap b = null;
                b = ScreenshotUtils.getScreenShot(relativeLayout);
                if (b != null) {
                    File saveFile = ScreenshotUtils.getMainDirectoryName(this);//get the path to save screenshot
                    File file = ScreenshotUtils.store(b, "screenshot.png", saveFile);//save the screenshot to selected path
                    shareScreenshot(file);//finally share screenshot
                } else
                    //If bitmap is null show toast message
                    Toast.makeText(this, "screenshot_take_failed", Toast.LENGTH_SHORT).show();
                //((AppCompatActivity) this).getSupportActionBar().show();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //share screenshot
    private void shareScreenshot(File file) {
        Uri uri = Uri.fromFile(file);//Convert file path into Uri for sharing
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);//pass uri here
        startActivity(Intent.createChooser(intent, ""));

    }

    //.............Jump to page no............

    private void gotoPage() {

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
        View mView = layoutInflaterAndroid.inflate(R.layout.dialog_gotopage, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
        alertDialogBuilderUserInput.setView(mView);

        final AlertDialog dialogBox1 = new AlertDialog.Builder(Mybook.this).setView(mView).setTitle("Go To Page").
                setPositiveButton("Go", null) //Set to null. We override the onclick
                .setNegativeButton("Cancel", null).create();

        edpageno = (EditText) mView.findViewById(R.id.edpageno);

        try {
            if (pageCount > 0) {
                int length = (int) Math.log10(pageCount) + 1;
                edpageno.setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        dialogBox1.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {

                Button b = dialogBox1.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        gopageno = edpageno.getText().toString();

                        if (!TextUtils.isEmpty(gopageno)) {

                            if (Double.parseDouble(gopageno) > pageCount) {
                                Toast.makeText(Mybook.this, "Page number not found", Toast.LENGTH_SHORT).show();

                            } else {

                                int gpage = Integer.parseInt(gopageno);
                                int no = gpage - 1;
                                pdfView.jumpTo(no);
                                dialogBox1.dismiss();
                            }
                        } else {
                            edpageno.requestFocus();
                            edpageno.setError("Enter PageNo");
                        }
                    }
                });

            }
        });
        dialogBox1.show();
    }

    //............ call when bookmark item clicked............
    private void bookmark() {

        //............create dialog box...................

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
        View mView = layoutInflaterAndroid.inflate(R.layout.dialog_bookmark, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
        alertDialogBuilderUserInput.setView(mView);

        final AlertDialog dialogBox = new AlertDialog.Builder(Mybook.this).setView(mView).setTitle("Bookmark").setPositiveButton("Add", null) //Set to null. We override the onclick
                .setNegativeButton("Cancel", null).create();

        ebookmark = (EditText) mView.findViewById(R.id.ebookmark);

        dialogBox.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface d) {

                Button b = dialogBox.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // TODO Do something

                        bookmarkname = ebookmark.getText().toString().trim();
                        if (!TextUtils.isEmpty(bookmarkname)) {

                            try {
                                //........... Method to Store the Data in SQLite Database..........
                                Cursor c1 = dbAdapter.checkForBookMark(String.valueOf(pageNumber + 1), bookmarkname);
                                int cnt = c1.getCount();

                                if (cnt == 0) {

                                    dbAdapter.insertBookMark(String.valueOf(pageNumber + 1), bookmarkname);
                                    Toast.makeText(Mybook.this, " Bookmark saved successfully", Toast.LENGTH_LONG).show();

                                    dialogBox.dismiss();
                                } else {
                                    dbAdapter.updateBookMark(String.valueOf(pageNumber + 1), bookmarkname);

                                    dialogBox.dismiss();
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {

                            try {
                                ebookmark.requestFocus();
                                ebookmark.setError("Enter BookMark Name");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
            }
        });

        dialogBox.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchPicker();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 505:
                switch (resultCode) {
                    case RESULT_OK:
                        String page = data.getStringExtra("pageno");
                        if (!TextUtils.isEmpty(page)) {
                            pageNumber = Integer.parseInt(page);
                            pageNumber = pageNumber - 1;
                            displayFromAsset();
                        }
                        break;
                }
                break;
        }
    }

    public static class IntentBuilder_ extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment fragment_;
        private android.support.v4.app.Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            super(context, Mybook.class);
        }

        public IntentBuilder_(Fragment fragment) {
            super(fragment.getActivity(), Mybook.class);
            fragment_ = fragment;
        }

        public IntentBuilder_(android.support.v4.app.Fragment fragment) {
            super(fragment.getActivity(), Mybook.class);
            fragmentSupport_ = fragment;
        }

        @Override
        public PostActivityStarter startForResult(int requestCode) {
            if (fragmentSupport_ != null) {
                fragmentSupport_.startActivityForResult(intent, requestCode);
            } else {
                if (fragment_ != null) {
                    if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
                        fragment_.startActivityForResult(intent, requestCode, lastOptions);
                    } else {
                        fragment_.startActivityForResult(intent, requestCode);
                    }
                } else {
                    if (context instanceof Activity) {
                        Activity activity = ((Activity) context);
                        ActivityCompat.startActivityForResult(activity, intent, requestCode, lastOptions);
                    } else {
                        if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
                            context.startActivity(intent, lastOptions);
                        } else {
                            context.startActivity(intent);
                        }
                    }
                }
            }
            return new PostActivityStarter(context);
        }
    }

    public static class NonConfigurationInstancesHolder {
        public Uri uri;
        public Object superNonConfigurationInstance;
        public Integer pageNumber;
    }
}



