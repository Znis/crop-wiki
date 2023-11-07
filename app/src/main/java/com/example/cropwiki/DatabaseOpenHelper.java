package com.example.cropwiki;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME="cropdata.db";
    private static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME="Table1";
    public static final String COL1="ID";
    public static final String COL2="name";
    public static final String COL3="temp_init";
    public static final String COL4="temp_final";
    public static final String COL5="humid_init";
    public static final String COL6="humid_final";
    public static final String COL7="season";
    public static final String COL8="description";

    //CONSTRUCTOR
    public DatabaseOpenHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);

    }
    public boolean addData(String name, String tempinit, String tempfinal, String humidinit,String humidfinal,String description,String season) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2, name);

        contentValues.put(COL3, tempinit);
        contentValues.put(COL4, tempfinal);
        contentValues.put(COL5, humidinit);
        contentValues.put(COL6, humidfinal);
        contentValues.put(COL7, season);
        contentValues.put(COL8, description);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor allData(){
        SQLiteDatabase  db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Table1",null);
        return cursor;




    }

    public boolean deleteData(String id){
        SQLiteDatabase  db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL1, id);
        contentValues.put(COL2, "0");


        contentValues.put(COL3, "0");
        contentValues.put(COL4,"0");
        contentValues.put(COL5, "0");
        contentValues.put(COL6, "0");
        contentValues.put(COL7, "0");
        contentValues.put(COL8, "0");
        db.update(TABLE_NAME,contentValues,"ID = ?",new String[] { id });
        return true;

        //return db.delete(TABLE_NAME,"ID = ?",new String[] {id});
    }
    public boolean updateData(String id,String name, String tempinit, String tempfinal, String humidinit,String humidfinal,String description,String season ){
        SQLiteDatabase  db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL2, id);
        contentValues.put(COL2, name);


        contentValues.put(COL3, tempinit);
        contentValues.put(COL4, tempfinal);
        contentValues.put(COL5, humidinit);
        contentValues.put(COL6, humidfinal);
        contentValues.put(COL7, season);
        contentValues.put(COL8, description);
        db.update(TABLE_NAME,contentValues,"ID = ?",new String[] { id });
        return true;

    }

}

