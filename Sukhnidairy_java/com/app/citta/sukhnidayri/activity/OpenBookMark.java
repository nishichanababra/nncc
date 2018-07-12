package com.app.citta.sukhnidayri.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.app.citta.sukhnidayri.R;
import com.app.citta.sukhnidayri.adapter.DBAdapter;
import com.app.citta.sukhnidayri.adapter.ItemAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class OpenBookMark extends AppCompatActivity {

    private DBAdapter dbAdapter;
    private ArrayList<BookData> bookMarkList;
    private ItemAdapter iadapter;
    private String currentpage = "";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_booklist);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }

        ListView lv1 = (ListView) findViewById(R.id.listview);
        dbAdapter = new DBAdapter(this);
        bookMarkList = new ArrayList<>();
        bookMarkList.clear();

        Cursor c1 = dbAdapter.getBookMarks();
        if (c1.getCount() > 0) {
            if (c1.moveToFirst()) {
                do {
                    String bName = c1.getString(c1.getColumnIndex("bookmarkname"));
                    String pageNo = c1.getString(c1.getColumnIndex("pageno"));

                    BookData bookData = new BookData(bName, pageNo);
                    bookMarkList.add(bookData);
                } while (c1.moveToNext());
            }
        }
        if (bookMarkList.size() == 0) {

            Toast.makeText(getApplicationContext(), " No BookMark Found ", Toast.LENGTH_LONG).show();
        } else {

            try {
                // ..............Initialize parameter to bind the Data in ListView...............

                iadapter = new ItemAdapter(getApplicationContext(), bookMarkList);
                lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                lv1.setAdapter(iadapter);
                // lv1.setAdapter(cursorAdapter);
                lv1.setLongClickable(true);
                lv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {

                        final int position = pos;
                        //selectedItem = bookMarkList.get(pos);
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(OpenBookMark.this);
                        alertDialog.setTitle("Delete Bookmark");
                        alertDialog.setMessage("Are you want to delete this bookmark?").setCancelable(true).
                                setNegativeButton("Go", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // if this button is clicked, close the dialog
                                        Intent intent = new Intent();
                                        intent.putExtra("pageno", bookMarkList.get(position).getPageNo());
                                        setResult(RESULT_OK, intent);
                                        finish();
                                    }
                                }).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                // if this dialogbox click delete the bookmark
                                dbAdapter.deleteNote(bookMarkList.get(position).getbName());
                                bookMarkList.remove(position);
                                iadapter.notifyDataSetChanged();
                                Toast.makeText(OpenBookMark.this, "Deleted Successfully.. ", Toast.LENGTH_LONG).show();

                            }
                        });
                        AlertDialog alertDialog1 = alertDialog.create();
                        alertDialog1.show();
                        return true;
                    }

                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public class BookData {
        String bName, pageNo;

        public BookData(String bName, String pageNo) {
            this.bName = bName;
            this.pageNo = pageNo;
        }

        public String getbName() {
            return bName;
        }

        public void setbName(String bName) {
            this.bName = bName;
        }

        public String getPageNo() {
            return pageNo;
        }

        public void setPageNo(String pageNo) {
            this.pageNo = pageNo;
        }
    }
}
