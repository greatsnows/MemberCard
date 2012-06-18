package sqlite3.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final int VERSION = 1;
	
	public DatabaseHelper (Context c, String name, CursorFactory f, int version){
		super(c, name, f, version);
	}
	
	public DatabaseHelper (Context c, String name){
		this(c, name, VERSION);
	}
	
	public DatabaseHelper (Context c, String name, int version){
		this(c, name, null, version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		System.out.println("create a Database");
		//execSQL函数用于执行SQL语句
		db.execSQL("create table cards(_id INTEGER PRIMARY KEY,name narchar(20),face varchar(40),back varchar(40))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("Upgrade a datebase！");

	}
}

