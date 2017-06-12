
package id.secure.passbank.login.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "login";
	private static final String TABLE_NAME = "user";
	private static final String nama = "nama";
	private static final String username = "username";
	private static final String password = "password";


	public DatabaseHelper(Context context, String db_name,
						  CursorFactory factory, int version) {
		super(context, DB_NAME, factory, version);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String create_table = "create table " + TABLE_NAME
				+ "(id integer primary key autoincrement,"
				+ nama 		+ " text,"
				+ username 	+ " text,"
				+ password 	+ " text);";
		db.execSQL(create_table);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
		onCreate(db);
	}

}
