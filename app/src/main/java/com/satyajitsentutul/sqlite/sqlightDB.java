package com.satyajitsentutul.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

public class sqlightDB extends SQLiteOpenHelper {

    private  static int DB_VERSION=1 ;
    private  static  String DB_Name="mydb.db";
    private  static  String TABLE_NAME="INFO";
    private  static  String COLUMN1="ID";
    private  static  String COLUMN2="name";
    private  static  String COLUMN3="cell";

    sqlightDB mydb ;

    public  sqlightDB(Context context){
       super(context,DB_Name,null,DB_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+TABLE_NAME+" (id  INTEGER PRIMARY KEY,name TEXT,cell TEXT) " ;
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    // Method for Inserting data
    public boolean insertData(String id, String name, String cell)
    {
        SQLiteDatabase db=getWritableDatabase(); // for write data
        ContentValues values=new ContentValues();
        values.put(COLUMN1,id);
        values.put(COLUMN2,name);
        values.put(COLUMN3,cell);
      long check=db.insert(TABLE_NAME,null,values);
      if(check==-1)
      {
          return  false ;
      }
      else
      {
          return true ;
      }
    }
    // method for viewing data
    public Cursor viewData()
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor result=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return result ;
    }
    // Method for updating data
    public boolean updateData(String id, String name, String cell)
    {
        SQLiteDatabase db=getWritableDatabase(); // for write data
        ContentValues values=new ContentValues();
        values.put(COLUMN1,id);
        values.put(COLUMN2,name);
        values.put(COLUMN3,cell);
        long check=db.update(TABLE_NAME,values,"id=?",new String[]{id});
        if(check==-1)
        {
            return  false ;
        }
        else
        {
            return true ;
        }
    }

    // Method for deleting data
    public int deleteData(String id)
    {
        SQLiteDatabase db=getWritableDatabase();
        return db.delete(TABLE_NAME,"id=?",new String[]{id}); //return 1 or -1
    }
}
