package cs585_hw3.team33.lib.db;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import cs585_hw3.team33.browse.list.Result;

public class DataHelper {
	
   private static final String DATABASE_NAME = "team33.db";
   private static final int DATABASE_VERSION = 4;
   private static final String TABLE_NAME = "blog_posts";
   
   private Context context;
   private SQLiteDatabase db = null;

   public DataHelper(Context context) {
      this.context = context;
   }
   
   public boolean isOpen() {
	   return db != null;
   }
   
   public void createDB() {
      //OpenHelper openHelper = new OpenHelper(this.context);
	  SQLiteOpenHelper openHelper = new SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
		  @Override
		  public void onCreate(SQLiteDatabase db) {
			 db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			 db.execSQL("CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY, X_Coord REAL, Y_Coord REAL, Post_Text TEXT)");
		  }

		  @Override
		  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			  Log.w("Example", "Upgrading database, this will drop tables and recreate.");
			  db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			  onCreate(db);
		  }
	  };
      this.db = openHelper.getWritableDatabase();
   }
   
   public void populateDB() {
	   insert(1,"500","600","Nasrullah Husami is a good boy");
       insert(2,"300","400","Anirudh Rekhi is a bad boy");
       insert(3,"700","800","Skyler Clark is a geek");
   }

   public void insert(int x, int y, String postTxt) {}
   public void insert(Integer id, String X, String Y, String entry) {
	   ContentValues values = new ContentValues();
	   values.put("id",id);
	   values.put("X_Coord",X);
	   values.put("Y_Coord",Y);
	   values.put("Post_Text", entry);
	   
	   @SuppressWarnings("unused")
	   long newid = this.db.insertOrThrow(TABLE_NAME, null, values);
   }

   public void deleteAll() {
      this.db.delete(TABLE_NAME, null, null);
   }

   public List<String> selectAll() {
      List<String> list = new ArrayList<String>();
      Cursor cursor = this.db.query(TABLE_NAME, new String[] { "id","X_Coord","Y_Coord","Post_Text" },null,null, null, null,null);
      
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
   
   public void query(int x, int y, String keywords, int k, ArrayList<Result> res ) {
	   String sql = "SELECT * FROM "+TABLE_NAME+"WHERE";
	   boolean b1st = true;
	   
	   StringTokenizer st = new StringTokenizer(keywords," ,\r\n");
	   while (st.hasMoreElements()) {
		   sql += (b1st?" ":" OR ") + "Post_Text LIKE '%" + st.nextToken() + "%'";
		   b1st = false;
	   }
	
	   sql += "ORDER BY (X_Coord-"+x+")*(X_Coord-"+x+") + (Y_Coord-"+y+")*(Y_Coord-"+y+") LIMIT BY " + k;
	   Cursor cursor = this.db.rawQuery(sql, 
			   new String[] { "id","X_Coord","Y_Coord","Post_Text" });
	   
	   res.clear();
	   if (cursor.moveToFirst()) {		   
           do {
              res.add( new Result(
            		  cursor.getInt(0),
            		  cursor.getInt(1),
            		  cursor.getInt(2),
            		  cursor.getString(3)
        		)); 
           } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
           cursor.close();
        }        
   }
}
