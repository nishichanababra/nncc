package com.app.citta.sukhnidayri.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.citta.sukhnidayri.database.DBHelper;

public class DBAdapter {

    private SQLiteDatabase sqldb;
    private DBHelper dbHelper;
    private Context context;

    public DBAdapter(Context c) {
        context = c;
    }

    //.........Function to Open DBHelper Connection in READ-WRITE Mode........

    private DBAdapter opnToWrite() {
        dbHelper = new DBHelper(context);
        try {
            sqldb = dbHelper.getWritableDatabase();
            return this;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    // .........Function to Close DBHelper Connection...........

    public void Close() {

        sqldb.close();
    }

    //.........Function for checkBookmark existance in database..........

    public Cursor checkForBookMark(String pageNo, String bName) {

        opnToWrite();
        Cursor c = null;
        String query1 = " SELECT * FROM bookmark where pageno = '" + pageNo + "' and bookmarkname = '" + bName + "' ";
        try {
            c = sqldb.rawQuery(query1, null);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    //......... Insert row in database............

    public long insertBookMark(String pageNo, String bName) {
        long val = 0;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("pageno", pageNo);
            contentValues.put("bookmarkname", bName);
            opnToWrite();

            val = sqldb.insert(dbHelper.BOOKMARK, null, contentValues);  //..... Method for insert row ....
            return val;
        } catch (Exception e) {
            System.out.print(e);
        }
        return val;
    }
    //............Function for update value in row .............

    public long updateBookMark(String pageNo, String bName) {
        long val = 0;
        try {
            ContentValues contentValues = new ContentValues();

            contentValues.put("pageno", pageNo);
            contentValues.put("bookmarkname", bName);
            opnToWrite();

            val = sqldb.update(DBHelper.BOOKMARK, contentValues, "pageno = '" + pageNo + "' AND bookmarkname = '" + bName + "' ", null);
            return val;
        } catch (Exception e) {
            System.out.print(e);
        }
        return val;
    }

    //........Function for find bookmarks from database.........

    public Cursor getBookMarks() {
        opnToWrite();
        Cursor c = null;
        String query1 = " SELECT * FROM bookmark ";
        try {
            c = sqldb.rawQuery(query1, null);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    //........... Delete bookmark.........

    public void deleteNote(String bName) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("bookmark", "bookmarkname" + " = ?", new String[]{bName});
        db.close();
    }
}
