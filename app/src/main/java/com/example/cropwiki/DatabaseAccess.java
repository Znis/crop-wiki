package com.example.cropwiki;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c= null;
    //private constructor so that objext creation from outside is avoided
    DatabaseAccess(Context context){
        this.openHelper=new DatabaseOpenHelper(context);

    }
    //to return single instance from database
    public static DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance=new DatabaseAccess(context);

        }
        return instance;
    }
    //to open the database
    public void open(){
        this.db=openHelper.getWritableDatabase();

    }
    //closing the database
    public void close(){
        if(db!=null){
            this.db.close();
        }
    }
    //method to take all data from database
    public Cursor allData(){
       // this.db= openHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Table1",null);
        return cursor;




    }



}
