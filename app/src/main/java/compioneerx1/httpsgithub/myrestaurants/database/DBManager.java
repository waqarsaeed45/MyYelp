package compioneerx1.httpsgithub.myrestaurants.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {


    static final String DBName = "yelp.db";//DATABASE NAME
    static final int DBVersion = 1;//version name
    Context context;

    static final String favRestaurants = "CREATE TABLE favRestaurants( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT ,phone TEXT, website TEXT,rating TEXT, imageUrl TEXT,price TEXT,address TEXT,latitude TEXT,longitude TEXT,categories TEXT,pushId TEXT);";

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBName, null, DBVersion);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(favRestaurants);
        Toast.makeText(context, "database created", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {

        Toast.makeText(context, "database updated", Toast.LENGTH_LONG).show();
        db.execSQL("Drop table IF EXISTS favRestaurants");
        onCreate(db);
    }


    public long Insert(ContentValues values) {
        SQLiteDatabase sqldb = this.getWritableDatabase();
        long result = sqldb.insert("favRestaurants", "", values);
        return result;
    }

    public long delete(ContentValues values) {
        SQLiteDatabase sqldb = this.getWritableDatabase();
        long result = sqldb.delete("favRestaurants", "_id=" + values.get("_id"), null);
        return result;
    }

    public Cursor getAllRestaurants() {
        SQLiteDatabase sqldbb = this.getReadableDatabase();
        Cursor cursor = sqldbb.query("favRestaurants", new String[]{"_id", "name",
                "phone", "website", "rating", "imageUrl", "price", "address", "latitude", "longitude", "categories", "pushId"}, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return cursor;
        } else {
            return null;
        }

    }
}
