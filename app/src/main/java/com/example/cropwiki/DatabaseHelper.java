package com.example.cropwiki;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="profile.db";
    public static final String TABLE_NAME="profile_table";
    public static final String COL1="ID";
    public static final String COL2="name";
    public static final String COL3="password";
    public static final String COL4="temperature";
    public static final String COL5="humidity";
    public static final String COL6="season";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE "+ TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, password TEXT, temperature TEXT,humidity TEXT, season TEXT)";
db.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);


    }
    public boolean insertData(String name, String password, String temperature, String humidity, String season){
      SQLiteDatabase  db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put(COL2, name);

        contentValues.put(COL3, password);
        contentValues.put(COL4, temperature);
        contentValues.put(COL5, humidity);
        contentValues.put(COL6, season);
        long result= db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }
    public Cursor getAllprofiles(){
        SQLiteDatabase  db = this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;

    }
    public boolean deleteData(String id){
        SQLiteDatabase  db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL1, id);
        contentValues.put(COL2, "0");

        contentValues.put(COL3, "0");
        contentValues.put(COL4, "0");
        contentValues.put(COL5, "0");
        contentValues.put(COL6, "0");
        db.update(TABLE_NAME,contentValues,"ID = ?",new String[] { id });
        return true;
        //return db.delete(TABLE_NAME,"ID = ?",new String[] {id});
    }
    public boolean updateData(String id,String name, String password, String temperature, String humidity, String season ){
        SQLiteDatabase  db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL2, id);
        contentValues.put(COL2, name);

        contentValues.put(COL3, password);
        contentValues.put(COL4, temperature);
        contentValues.put(COL5, humidity);
        contentValues.put(COL6, season);
        db.update(TABLE_NAME,contentValues,"ID = ?",new String[] { id });
        return true;

    }
}
