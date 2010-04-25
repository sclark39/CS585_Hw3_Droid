package com.totsp.androidexamples;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataHelper {

   private static final String DATABASE_NAME = "example.db";
   private static final int DATABASE_VERSION = 1;
   private static final String TABLE_NAME = "table1";
   
   public static final String KEY_ROWID = "id";
   
   private Context context;
   private SQLiteDatabase db;

   public DataHelper(Context context) {
      this.context = context;
      OpenHelper openHelper = new OpenHelper(this.context);
      this.db = openHelper.getWritableDatabase();
   }

   public void insert(String name) {
	   ContentValues values = new ContentValues();
	   values.put("name",name);
	   this.db.insert(TABLE_NAME, null, values);
   }

   public void deleteAll() {
      this.db.delete(TABLE_NAME, null, null);
   }

   public List<String> selectAll() {
      List<String> list = new ArrayList<String>();
      Cursor cursor = this.db.query(TABLE_NAME, new String[] { "name" }, 
        null, null, null, null, "name desc");
      if (cursor.moveToFirst()) {
         do {
            list.add(cursor.getString(0)); 
         } while (cursor.moveToNext());
      }
      if (cursor != null && !cursor.isClosed()) {
         cursor.close();
      }
      return list;
   }
   
   public List<String> fetchNote(long rowId) throws SQLException {
	   List<String> list = new ArrayList<String>();

       Cursor cursor =  this.db.query(true, TABLE_NAME, new String[] { "name" }, KEY_ROWID + "=" + rowId, null,
                   null, null, null, null);
       if (cursor.moveToFirst()) {
           do {
              list.add(cursor.getString(0)); 
           } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
           cursor.close();
        }
       
        return list;

   }

   private static class OpenHelper extends SQLiteOpenHelper {

      OpenHelper(Context context) {
         super(context, DATABASE_NAME, null, DATABASE_VERSION);
      }

      @Override
      public void onCreate(SQLiteDatabase db) {
         db.execSQL("CREATE TABLE " + TABLE_NAME +  
          "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");
      }

      @Override
      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         Log.w("Example", "Upgrading database, this will drop tables and recreate.");
         db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
         onCreate(db);
      }
   }
}
