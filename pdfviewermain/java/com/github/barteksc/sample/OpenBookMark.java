package com.github.barteksc.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.sample.database.DBAdapter;
import com.github.barteksc.sample.database.itemAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class OpenBookMark extends Activity {

    DBAdapter dbAdapter;
    Context context;
    SQLiteDatabase sqldb;
    ArrayList<HashMap<String, String>> bookMarkList;
    ArrayAdapter arrayAdapter;
    itemAdapter iadapter;
    ListView lv1;
    TextView tv1,tDeleteBookmark;
    String bName, pageNo,selectedItem ;
    PDFView pdfView;
    PDFViewActivity pdfViewActivity;
    Dialog dialogBookmark;
    Button delete,go;
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.booklist);
        tv1 = (TextView) findViewById(R.id.tv1);
        lv1 = (ListView) findViewById(R.id.listview);
        tv1.setText("MY BOOKMARKS");

        dbAdapter = new DBAdapter(this);
        bookMarkList = new ArrayList<HashMap<String, String>>();
        bookMarkList.clear();

        Cursor c1 = dbAdapter.getBookMarks();
        if (c1.getCount() > 0) {
            if (c1.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    bName = c1.getString(c1.getColumnIndex("bookmarkname"));
                    pageNo = c1.getString(c1.getColumnIndex("pageno"));
                    map.put("PageNumber",  pageNo);
                    map.put("BookMarkName", bName);
                    bookMarkList.add(map);
                }
                while (c1.moveToNext());
            }
        }
        if (bookMarkList.size() == 0) {

            Toast.makeText(getApplicationContext(), " Please Bookmark Page ",
                    Toast.LENGTH_LONG).show();
        } else {

            try {
                // ..............Initialize parameter to bind the Data in ListView...............

                iadapter = new itemAdapter(getApplicationContext(), bookMarkList);
                lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                lv1.setAdapter(iadapter);
                lv1.setLongClickable(true);
                lv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                    //............ long press on item.............

                    public boolean onItemLongClick(final AdapterView<?> parent, final View view, final int pos, long id) {

                        selectedItem = parent.getItemAtPosition(pos).toString();

                        AlertDialog.Builder alertDialog=new AlertDialog.Builder(OpenBookMark.this);
                        alertDialog.setTitle("Delete Bookmark");
                       // alertDialog.setIcon(R.drawable.delete);

                        alertDialog
                                .setMessage("Are you want to delete this bookmark or go to bookmark page")
                                .setCancelable(true)
                                .setPositiveButton("GoTo",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // if this button is clicked, close
                                        // current activity
                                        Intent in = new Intent(OpenBookMark.this,PDFViewActivity_.class);
                                        startActivity(in);
                                        Toast.makeText(getApplicationContext(),"go", Toast.LENGTH_LONG).show();
                                        //dialogBookmark.dismiss();
                                       // OpenBookMark.this.finish();
                                    }
                                })
                                .setNegativeButton("Delete",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // if this button is clicked, just close
                                        // the dialog box and do nothing
                                        

                                        bookMarkList.remove(selectedItem);
                                        iadapter.notifyDataSetChanged();
                                        // Toast.makeText(getApplicationContext(),"delete"+ valuebpage+  " name is "+ valuebname,Toast.LENGTH_LONG).show();
                                       // dialogBookmark.dismiss();
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alertDialog1=alertDialog.create();
                        alertDialog1.show();





                       /* dialogBookmark = new Dialog(OpenBookMark.this);
                        dialogBookmark.setContentView(R.layout.deletebookmark);
                        dialogBookmark.setTitle("Bookmarks");
                        dialogBookmark.setCancelable(true);
                        tDeleteBookmark =(TextView)findViewById(R.id.tDeleteBookmark);
                        delete= (Button) dialogBookmark.findViewById(R.id.delete);
                        go= (Button) dialogBookmark.findViewById(R.id.gotopage);*/

                        //..............delete item..............

                        /*delete.setOnClickListener(new View.OnClickListener() {

                            public void onClick(View v) {

                                TextView tvpagename=(TextView)findViewById(R.id.tvbname);
                                String valuebname=tvpagename.getText().toString();
                                TextView tvpageno=(TextView)findViewById(R.id.tvpage);
                                String valuebpage=tvpageno.getText().toString();
                                dbAdapter.deleteBookmark(valuebpage,valuebname);
                                bookMarkList.remove(selectedItem);
                                iadapter.notifyDataSetChanged();
                                // Toast.makeText(getApplicationContext(),"delete"+ valuebpage+  " name is "+ valuebname,Toast.LENGTH_LONG).show();
                                dialogBookmark.dismiss();



                            }
                        });*/

                        //................ got o bookmark page...........
/*
                        go.setOnClickListener(new View.OnClickListener() {

                            public void onClick(View v) {

                                Intent in = new Intent(OpenBookMark.this,PDFViewActivity_.class);
                                startActivity(in);
                                Toast.makeText(getApplicationContext(),"go", Toast.LENGTH_LONG).show();
                                dialogBookmark.dismiss();
                            }
                        });*/
                        //dialogBookmark.show();
                        return true;
                    }

                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
