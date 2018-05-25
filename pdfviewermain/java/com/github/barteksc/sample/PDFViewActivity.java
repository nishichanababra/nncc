/**
 * Copyright 2016 Bartosz Schiller
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.barteksc.sample;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.github.barteksc.sample.database.DBAdapter;
import com.shockwave.pdfium.PdfDocument;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.options)
public class PDFViewActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener,
        OnPageErrorListener {

    private static final String TAG = "PDFViewActivity";

    private final static int REQUEST_CODE = 42;
    public static final int PERMISSION_CODE = 42042;

    public static final String SAMPLE_FILE = "sample.pdf";
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    Dialog dialog;
    Layout lvCustomerList;
    ArrayAdapter arrayAdapter;
    DBAdapter dbAdapter;
    TextView tpno;
    EditText edpageno;
    int gpage;
    String pdfFile;
    Button bremove, badd, cancel, ok;
    String t, gopageno, str, bookmarkname;
    public EditText ebookmark;
    protected TextView textView, tname;
    Editable bname;

    @ViewById
    PDFView pdfView;

    @NonConfigurationInstance
    Uri uri;

    @NonConfigurationInstance
    Integer pageNumber = 0;

    String pdfFileName;

    @OptionsItem(R.id.pickFile)
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

    @AfterViews
    void afterViews() {
        pdfView.setBackgroundColor(Color.LTGRAY);
        if (uri != null) {
            displayFromUri(uri);
        } else {
            displayFromAsset(SAMPLE_FILE);
        }
        setTitle(pdfFileName);
    }

    private void displayFromAsset(String assetFileName) {
        pdfFileName = assetFileName;

        pdfView.fromAsset(SAMPLE_FILE).defaultPage(pageNumber).onPageChange(this).enableAnnotationRendering(true).onLoad(this).scrollHandle(new DefaultScrollHandle(this)).spacing(10) // in dp
                .onPageError(this).pageFitPolicy(FitPolicy.BOTH).load();
    }

    private void displayFromUri(Uri uri) {
        pdfFileName = getFileName(uri);

        pdfView.fromUri(uri).defaultPage(pageNumber).onPageChange(this).enableAnnotationRendering(true).onLoad(this).scrollHandle(new DefaultScrollHandle(this)).spacing(10) // in dp
                .onPageError(this).load();
    }

    @OnActivityResult(REQUEST_CODE)
    public void onResult(int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            uri = intent.getData();
            displayFromUri(uri);
        }
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        Log.e(TAG, "title = " + meta.getTitle());
        Log.e(TAG, "author = " + meta.getAuthor());
        Log.e(TAG, "subject = " + meta.getSubject());
        Log.e(TAG, "keywords = " + meta.getKeywords());
        Log.e(TAG, "creator = " + meta.getCreator());
        Log.e(TAG, "producer = " + meta.getProducer());
        Log.e(TAG, "creationDate = " + meta.getCreationDate());
        Log.e(TAG, "modDate = " + meta.getModDate());

        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    /**
     * Listener for response to user permission request
     *
     * @param requestCode  Check that permission request code matches
     * @param permissions  Permissions that requested
     * @param grantResults Whether permissions granted
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchPicker();
            }
        }
    }

    @Override
    public void onPageError(int page, Throwable t) {
        Log.e(TAG, "Cannot load page " + page);
    }


    //..........set itemclick Listener .............

    /*public boolean onOptionsItemSelected(MenuItem item) {
        int itemId_ = item.getItemId();
        if (itemId_ == R.id.bookmark) {
            bookmark();
            return true;

        } else if (itemId_ == R.id.search) {
           //    getsearch();
            return true;
        } else if (itemId_ == R.id.open) {
            getlist();
        } else if (itemId_ == R.id.go) {
            gotoPage();
        }
        return super.onOptionsItemSelected(item);
    }

    //............ call when Search  item clicked............

    private void getsearch() {

        Toast.makeText(getApplicationContext(), "search word ", Toast.LENGTH_LONG).show();
    }

    //.............Jump to page no............

    private void gotoPage() {

        dialog = new Dialog(PDFViewActivity.this);
        dialog.setContentView(R.layout.gotopage);
        dialog.setTitle("Jump to page");
        dialog.setCancelable(true);
        //tpno = (TextView) findViewById(R.id.tpno);
        edpageno = (EditText) dialog.findViewById(R.id.edpageno);
        cancel = (Button) dialog.findViewById(R.id.cancel);
        ok = (Button) dialog.findViewById(R.id.ok);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gopageno = edpageno.getText().toString();
                int gpage = Integer.parseInt(gopageno);
                int no = gpage - 1;
                pdfView.jumpTo(no);
                Toast.makeText(getApplicationContext(), "go to page " + gopageno, Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }

        });
        dialog.getWindow().setLayout(900, 900);
        dialog.show();

    }

    //............ call when Dictionary  item clicked............

  *//*  private void getdic() {

        Uri uri = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "search on dic", Toast.LENGTH_LONG).show();
    }*//*
    //............ call when open bookmark list item clicked............

    private void getlist() {

        Intent in3 = new Intent(PDFViewActivity.this, OpenBookMark.class);
        startActivity(in3);
    }

    //............ call when bookmark item clicked............

    private void bookmark() {

        //............create dialog box...................

        dialog = new Dialog(PDFViewActivity.this);
        dialog.setContentView(R.layout.dialogbookmark);
        dialog.setTitle("Give bookmark name");
        dialog.setCancelable(true);
        ebookmark = (EditText) dialog.findViewById(R.id.ebookmark);
        bremove = (Button) dialog.findViewById(R.id.bremove);
        badd = (Button) dialog.findViewById(R.id.badd);
        badd.setOnClickListener(new View.OnClickListener() {           //..............add button clicked..........
            public void onClick(View v) {
                t = (String) getTitle();
                bname = null;
                str = t.substring(0, t.indexOf("/"));
                bookmarkname = ebookmark.getText().toString();
                if (bookmarkname.equals("") || bookmarkname.length() <= 0) {
                    Toast.makeText(PDFViewActivity.this, " Enter Book Mark Name ", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        //........... Method to Store the Data in SQLite Database..........

                        Cursor c1 = dbAdapter.checkForBookMark(str, bookmarkname);
                        int cnt = c1.getCount();
                        if (cnt == 0)

                        {
                            dbAdapter.insertBookMark(str, bookmarkname);
                            Toast.makeText(PDFViewActivity.this, "bookmark successfully", Toast.LENGTH_LONG).show();
                        } else

                        {
                            dbAdapter.updateBookMark(str, bookmarkname);
                        }

                        dialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }
        });
    }*/
}
