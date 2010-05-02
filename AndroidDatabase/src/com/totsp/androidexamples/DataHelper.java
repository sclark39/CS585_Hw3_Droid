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

   private static final String DATABASE_NAME = "example2.db";
   private static final int DATABASE_VERSION = 4;
   private static final String TABLE_NAME = "table2";
   public static final String KEY_ROWID = "id";
   public static final String MICROBLOG = "microblog";
   
   private Context context;
   private SQLiteDatabase db;

   public DataHelper(Context context) {
      this.context = context;
      CreateDB();
   }
   
   public void CreateDB() {
      OpenHelper openHelper = new OpenHelper(this.context);
      this.db = openHelper.getWritableDatabase();
   }

   public void insert(Integer id, String X, String Y, String entry) {
	   ContentValues values = new ContentValues();
	   values.put("id",id);
	   values.put("X_Coord",X);
	   values.put("Y_Coord",Y);
	   values.put(MICROBLOG, entry);
	   long newid = this.db.insertOrThrow(TABLE_NAME, null, values);
   }

   public void deleteAll() {
      this.db.delete(TABLE_NAME, null, null);
   }

   public List<String> selectAll() {
      List<String> list = new ArrayList<String>();
      Cursor cursor = this.db.query(TABLE_NAME, new String[] { "id","X_Coord","Y_Coord",MICROBLOG },null,null, null, null,null);
      
      long count = cursor.getCount();
      
      if (cursor.moveToFirst()) {
         do {
            list.add(cursor.getString(0)); 
            list.add(cursor.getString(1)); 
            list.add(cursor.getString(2)); 
            list.add(cursor.getString(3));
         } while (cursor.moveToNext());
      }
      if (cursor != null && !cursor.isClosed()) {
         cursor.close();
      }
      return list;
   }
   
   public List<String> fetchNote(long rowId) throws SQLException {
	   List<String> list = new ArrayList<String>();

       //Cursor cursor =  this.db.query(TABLE_NAME, new String[] { MICROBLOG }, KEY_ROWID + "=" + rowId, null, null, null, null);
	   int LOCX =1000;
	   int LOCY =2000;
	   
	   Cursor cursor =  this.db.query(TABLE_NAME, new String[] { KEY_ROWID }, MICROBLOG +" like " + "'%GOOD%'", null, null, null,("X_Coord" - LOCX)*("X_Coord" - LOCX)+("Y_Coord" - LOCY)*("Y_Coord" - LOCY););
	   //Cursor cursor =  this.db.query(TABLE_NAME, new String[] { KEY_ROWID }, MICROBLOG +" like " + "'%bad%'" + "OR" + "'%GOOD%'", null, null,null, null);
       
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
    	 db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
         db.execSQL("CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY, X_Coord REAL, Y_Coord REAL, microblog TEXT)");
         //db.execSQL("CREATE TABLE " + STRING_TABLE + "(Entry_id INTEGER  , Tag_id INTEGER , microblog_keyword TEXT,PRIMARY KEY (Entry_id,Tag_id), FOREIGN KEY(Entry_id) REFERENCES table2(id)");
      }

      @Override
      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         Log.w("Example", "Upgrading database, this will drop tables and recreate.");
         db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
         onCreate(db);
      }
   }
}
