package com.androiddeveloper.sheikhameen.projectideaspredictor;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBHelper extends SQLiteAssetHelper {
    public static final String Db_name="IDEASS.db";
    public static final int Version=1;

    public DBHelper(Context context)
    {
        super(context,Db_name,null,Version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);

    }

}
