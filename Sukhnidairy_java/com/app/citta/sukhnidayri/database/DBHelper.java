package com.app.citta.sukhnidayri.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PDFREADER";
    private static final int VERSION = 1;
    public static final String BOOKMARK = "bookmark";
    private static final String KEY_ID = "_id";
    private static final String KEY_PAGE = "pageno";
    private static final String KEY_BNAME = "bookmarkname";
    private static String SQL_BOOKMARK_PAGE = "CREATE TABLE bookmark(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," + "pageno TEXT DEFAULT NULL," + "bookmarkname TEXT DEFAULT NULL" + ")";
    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.db = db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_BOOKMARK_PAGE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ..........Query to CREATE TABLE bookmark..........

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE " + BOOKMARK);
        onCreate(db);
    }

}
