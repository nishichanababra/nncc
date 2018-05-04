package app.citta.utilities.GeneralizedModules.Firebase.LocalDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * Created by ws-16 on 7/19/2017.
 */

public class DBAdapter {

    private SQLiteDatabase sqLiteDatabase;
    private DBHelper dbHelper;
    private Context context;

    public DBAdapter(Context context) {
        this.context = context;
    }

    public DBAdapter openToRead() throws SQLiteException {
        dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getReadableDatabase();
        return this;
    }

    public DBAdapter openToWrite() throws SQLiteException {
        dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return this;
    }

    /* **************************  This two methods for firebase push notifications save and get ************************* */
    public long saveNewFirebaseNotification(String token, String data, String time, String status) {
        openToWrite();
        long value = 0;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("TOKEN", token);
            contentValues.put("DATA", data);
            contentValues.put("TIME", time);
            contentValues.put("STATUS", status);

            value = sqLiteDatabase.insert(DBHelper.TBL_NOTIFICATIONS, null, contentValues);
            return value;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public Cursor getAllFirebaseNotifications() {
        openToRead();
        Cursor cursor = null;

        String query = "SELECT * FROM TBL_NOTIFICATIONS";
        try {
            cursor = sqLiteDatabase.rawQuery(query, null);
            return cursor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursor;
    }

    public void deletePlanVisitData(String id) {
        try {
            openToWrite();
            sqLiteDatabase.delete(DBHelper.TBL_NOTIFICATIONS, "ID = ' " + id + " ' ", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
