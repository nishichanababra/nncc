package app.citta.utilities.GeneralizedModules.Firebase.LocalDB;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ws-16 on 7/19/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;
    private static final String DATABASENAME = "CITTA_UTILITIES";
    private static final int VERSION = 1;


    public static final String TBL_NOTIFICATIONS = "TBL_NOTIFICATIONS";
    private static final String EXEC_NOTIFICATIONS = "CREATE TABLE TBL_NOTIFICATIONS(" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "TOKEN TEXT NULL DEFAULT NULL," +
            "DATA TEXT NULL DEFAULT NULL," +
            "TIME TEXT NULL DEFAULT NULL," +
            "STATUS TEXT NULL DEFAULT NULL" + ")";


    DBHelper(Context context) {
        super(context, DATABASENAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(EXEC_NOTIFICATIONS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE " + TBL_NOTIFICATIONS);
        onCreate(db);
    }
}
