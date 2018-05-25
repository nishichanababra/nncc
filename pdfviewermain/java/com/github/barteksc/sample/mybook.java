package com.github.barteksc.sample;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.Notification;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;

import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.github.barteksc.sample.database.DBAdapter;

import java.util.ArrayList;
import java.util.HashMap;



public final class mybook extends PDFViewActivity implements HasViews, OnViewChangedListener
{
    private static final int PICK_CONTACT = 0;
    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    public PDFView pdfview;
    protected TextView textView, tname;
    private Context context;

    String t,gopageno,str,bookmarkname;
    ListView listView;
    Editable bname;
    Dialog dialog;
    Button bremove, badd,cansel,ok;
    ArrayList<HashMap<String, String>> bookMarkList;
    Layout  lvCustomerList;
    ArrayAdapter arrayAdapter;
    DBAdapter dbAdapter;
    TextView tpno;
    EditText edpageno;
    int gpage;
    private final static int REQUEST_CODE = 42;
   // private GoogleApiClient client;
   final Context c = this;
   EditText ebookmark;




    public void onCreate(Bundle savedInstanceState)
    {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);

        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(R.layout.activity_main);

        dbAdapter = new DBAdapter(this);
        listView=(ListView)findViewById(R.id.listview);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       // client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }






    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        NonConfigurationInstancesHolder nonConfigurationInstance = ((NonConfigurationInstancesHolder) super.getLastCustomNonConfigurationInstance());
        if (nonConfigurationInstance != null) {
            uri = nonConfigurationInstance.uri;
            pageNumber = nonConfigurationInstance.pageNumber;
        }
    }
    @Override  //.........set layout of page..............

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

    public static IntentBuilder_ intent(Context context) {
        return new IntentBuilder_(context);
    }

    public static IntentBuilder_ intent(Fragment fragment) {
        return new IntentBuilder_(fragment);
    }

    public static IntentBuilder_ intent(android.support.v4.app.Fragment supportFragment) {
        return new IntentBuilder_(supportFragment);
    }

    @Override  //........call when page view is changed...........

    public void onViewChanged(HasViews hasViews) {
        this.pdfView = ((PDFView) hasViews.findViewById(R.id.pdfView));
        afterViews();
    }

    @Override   //.......get last stored page .......

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
        return true;

    }


       //..........set itemclick Listener .............

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int itemId_ = item.getItemId();
        if (itemId_ == R.id.bookmark) {
            bookmark();
            return true;

        } else if (itemId_ == R.id.search) {
            getsearch();
            return true;

        }
        else if (itemId_ == R.id.open) {
            getlist();
        }
        else if (itemId_ == R.id.go) {
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

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
        View mView = layoutInflaterAndroid.inflate(R.layout.gotopage, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
        alertDialogBuilderUserInput.setView(mView);

        edpageno = (EditText) mView.findViewById(R.id.edpageno);

        alertDialogBuilderUserInput.setTitle("GoToPage").setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                gopageno=edpageno.getText().toString();
                int gpage=Integer.parseInt(gopageno);
                int no=gpage - 1;
                pdfView.jumpTo(no);
               // Toast.makeText(getApplicationContext(), "go to page "  +  gopageno, Toast.LENGTH_LONG).show();
                //dialog.dismiss();
            }


        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        dialogBox.cancel();
                    }
                });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();

    }




        /*dialog = new Dialog(mybook.this);
        dialog.setContentView(R.layout.gotopage);
        dialog.setTitle("Jump to page");
        dialog.setCancelable(true);
       // tpno =(TextView)findViewById(R.id.tpno);
        edpageno = (EditText) dialog.findViewById(R.id.edpageno);
        cansel = (Button) dialog.findViewById(R.id.cancel);
        ok = (Button) dialog.findViewById(R.id.ok);
        cansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gopageno=edpageno.getText().toString();
                int gpage=Integer.parseInt(gopageno);
                int no=gpage - 1;
               pdfView.jumpTo(no);
                Toast.makeText(getApplicationContext(), "go to page "  +  gopageno, Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }

        });
        dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        dialog.show();*/




    //............ call when open bookmark list item clicked............

    private void getlist() {

            Intent in3= new Intent(mybook.this,OpenBookMark.class);
            startActivity(in3);
    }

    //............ call when bookmark item clicked............

    private void bookmark() {

        //............create dialog box...................


        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
        View mView = layoutInflaterAndroid.inflate(R.layout.dialogbookmark, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
        alertDialogBuilderUserInput.setView(mView);

         ebookmark = (EditText) mView.findViewById(R.id.ebookmark);

        alertDialogBuilderUserInput.setTitle("Bookmark").setCancelable(true).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogBox, int id) {
                // ToDo get user input here

                t = (String) getTitle();
                bname = null;
                str = t.substring(0, t.indexOf("/"));
                bookmarkname = ebookmark.getText().toString();
                if (bookmarkname.equals("") || bookmarkname.length() <= 0) {
                    Toast.makeText(mybook.this, " Enter Book Mark Name ", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        //........... Method to Store the Data in SQLite Database..........

                        Cursor c1 = dbAdapter.checkForBookMark(str, bookmarkname);
                        int cnt = c1.getCount();
                        if (cnt == 0)

                        {
                            dbAdapter.insertBookMark(str, bookmarkname);
                            Toast.makeText(mybook.this, "bookmark successfully", Toast.LENGTH_LONG).show();
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
        })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        dialogBox.cancel();
                    }
                });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();

    }


    public static class IntentBuilder_
            extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment fragment_;
        private android.support.v4.app.Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            super(context, mybook.class);
        }

        public IntentBuilder_(Fragment fragment) {
            super(fragment.getActivity(), mybook.class);
            fragment_ = fragment;
        }

        public IntentBuilder_(android.support.v4.app.Fragment fragment) {
            super(fragment.getActivity(), mybook.class);
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

    private static class NonConfigurationInstancesHolder {
        public Uri uri;
        public Object superNonConfigurationInstance;
        public Integer pageNumber;
    }
}



