package com.github.barteksc.sample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PDFREADER";
    public static final int VERSION = 1;
    public static final String BOOKMARK = "bookmark";
    private static final String KEY_ID = "_id";
    private static final String KEY_PAGE = "pageno";
    private static final String KEY_BNAME = "bookmarkname";
    public static String SQL_BOOKMARK_PAGE = "CREATE TABLE bookmark(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," + "pageno TEXT NULL DEFAULT NULL," + "bookmarkname TEXT NULL DEFAULT NULL" + ")";
    SQLiteDatabase db;

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

  /*  // Adding new User Details
    void insertUserDetails(String page, String bname) {
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_PAGE, page);
        cValues.put(KEY_BNAME, bname);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(BOOKMARK, null, cValues);
        db.close();
    }

    // Get User Details
    public ArrayList<HashMap<String, String>> GetUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, location, designation FROM " + BOOKMARK;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("page", cursor.getString(cursor.getColumnIndex(KEY_PAGE)));
            user.put("bname", cursor.getString(cursor.getColumnIndex(KEY_BNAME)));
            userList.add(user);
        }
        return userList;
    }
    // Delete User Details
    public void DeleteUser(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BOOKMARK, KEY_ID+" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }
    // Update User Details
    public int UpdateUserDetails(String location, String designation, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_PAGE, location);
        cVals.put(KEY_BNAME, designation);
        int count = db.update(BOOKMARK, cVals, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }*/
}
