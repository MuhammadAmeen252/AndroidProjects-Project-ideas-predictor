package com.androiddeveloper.sheikhameen.projectideaspredictor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBaccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DBaccess instance;
    private DBaccess(Context context)
    {
        this.openHelper=new DBHelper(context);
    }
    public static DBaccess getInstance(Context context) {
        if (instance == null) {
            instance = new DBaccess (context);
        }
        return instance;
    }
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }
    public ArrayList<String> getCategories() {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT category FROM IDEAS1", null);
        cursor.moveToFirst();
        String category="";
        boolean is_present=false;
        while (!cursor.isAfterLast()) {
            category=cursor.getString(0);

            is_present=list.contains(category);
            if(!is_present)
            {
                list.add(category);
            }
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public ArrayList<String> getIdeas(String Category) {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT ideas FROM IDEAS1 where category= '"+Category+"'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public String getInstruction(String idea)
    {
        this.open();
        String instruction="";
        Cursor c= database.rawQuery("SELECT ID FROM IDEAS1 where ideas= '"+idea+"'", null);
        c.moveToFirst();
        String id=c.getString(0);
        Cursor cursor = database.rawQuery("SELECT instruction FROM IDEAS1 where ID= '"+id+"'", null);
        cursor.moveToFirst();
        instruction=cursor.getString(0);
        cursor.close();
        c.close();
        this.close();
        return instruction;
    }

    public long delete(String del)
    {
        return database.delete("IDEAS1","category=?",new String[]{del});
    }

}
