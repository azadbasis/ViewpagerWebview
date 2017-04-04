package com.server.sync.azhar.sqlitesyncdemoone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Azhar on 3/31/2017.
 */

 public class DBHelper extends SQLiteOpenHelper {


    private static final int DATABASE_BERSION = 1;
    private  static final String CREATE_TABLE = "create table "+DbContact.TABLE_NAME +
            " ( "+DbContact.ID+" integer primary key, " +
            DbContact.NAME+" text,"+
            DbContact.SYNC_STATUS+" integer);";


     private static final String DROP_TABLE = "drop table if exists "+DbContact.TABLE_NAME;

    Context context;
    public DBHelper(Context context) {
        super(context, DbContact.DB_NAME, null, DATABASE_BERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void saveToLocalDatabase(String name,int syncStatus,SQLiteDatabase sqLiteDatabase){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContact.NAME,name);
        contentValues.put(DbContact.SYNC_STATUS,syncStatus);
        sqLiteDatabase.insert(DbContact.TABLE_NAME,null,contentValues);
    }
    public Cursor readFromLocalDatabase(SQLiteDatabase sqLiteDatabase){

        String[] projection = {DbContact.ID,DbContact.NAME,DbContact.SYNC_STATUS};
        return (sqLiteDatabase.query(DbContact.TABLE_NAME,projection,null,null,null,null,null));
    }

    public void updateLocalDatabase(String name, int syncStatus,SQLiteDatabase sqLiteDatabase,int id){
        DBHelper dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContact.SYNC_STATUS,syncStatus);
       // String selection = DbContact.NAME+" LIKE ?";
        String selection = DbContact.ID+"="+id;
        //String[] selection_arg = {name};
      //  sqLiteDatabase.update(DbContact.TABLE_NAME,contentValues,selection,selection_arg);
        sqLiteDatabase.update(DbContact.TABLE_NAME,contentValues,selection,null);
        dbHelper.close();
    }
}
