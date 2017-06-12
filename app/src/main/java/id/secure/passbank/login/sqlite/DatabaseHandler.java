package id.secure.passbank.login.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHandler {

	private static final String DB_NAME = "login";
	private static final String TABLE_NAME = "user";

	private static final int DATABASE_VERSION = 1;
	private SQLiteDatabase sqliteDB;
	private DatabaseHelper dbHelper;

	public DatabaseHandler(Context context) {
		dbHelper = new DatabaseHelper(context, DB_NAME, null, DATABASE_VERSION);
	}
	
	// Open Database
	public void open() {
		sqliteDB = dbHelper.getWritableDatabase();
	}
	
	// Close Database
	public void close() { 
		if (sqliteDB != null)
			sqliteDB.close();
	}

	public Cursor getUserInfo() {

		Cursor cursor = sqliteDB.query(TABLE_NAME, null, null, null, null,null, null);
		cursor.moveToFirst();
	
		return cursor;
	}
	
	 public long insertHistory(String nama, String username, String password){
	    
		 	ContentValues object = new ContentValues();
			sqliteDB = dbHelper.getWritableDatabase();
			object.put("nama",nama);
	    	object.put("username",username);
	    	object.put("password",password);

	    
	    	//sqliteDB.close();
	    	return sqliteDB.insert(TABLE_NAME, null, object);
			
	 }
	 
	public void deleteAll() {
		sqliteDB = dbHelper.getWritableDatabase();
		sqliteDB.delete(TABLE_NAME, null, null);
		sqliteDB.close();
	}
	
	public int getCount(String username,String password) {
			String countQuery ="SELECT  * FROM user where username LIKE '%"+username+ "%' AND password LIKE '%"+password+ "%'";
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);
			return cursor.getCount();
		}
	 

}
