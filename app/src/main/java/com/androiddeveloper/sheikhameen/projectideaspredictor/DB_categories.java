package com.androiddeveloper.sheikhameen.projectideaspredictor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DB_categories {
    public static final String key_id="_id";
    public static final String categories="_category";
    public final String db_name="Tabl";
    //public final String db_location="C:\\Users\\Sheikh Ameen\\AndroidStudioProjects\\ProjectIdeasPredictor\\app\\src\\main\\assets\\databases"
    public final String ideas="_ideas";
    public final String instructions="_instructions";
    public final int version=1;
    public static final String db_table="db_table";

    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private DB_helper helper;
    public DB_categories(Context context)
    {
        this.context=context;
    }
    public class DB_helper extends SQLiteOpenHelper{


        public DB_helper(Context context) {
            super(context, db_name, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sqlCode="CREATE TABLE "+db_table+" ( "+key_id+"Integer primary key autoincrement, "+
                    categories+"TEXT NOT NULL, "+ideas+" TEXT NOT NULL, "+instructions+" TEXT NOT NULL"+ " );";
            db.execSQL(sqlCode);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("Drop table if exists "+db_table);
            onCreate(db);
        }

    }
    public DB_categories open() throws SQLException{
        helper=new DB_helper(context);
        sqLiteDatabase =helper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        helper.close();
    }
    public long AddCategory(String category,String idea,String instructions)
    {
        ContentValues cv=new ContentValues();
        cv.put(ideas,idea);
        cv.put(categories,category);
        cv.put(this.instructions,instructions);
        return sqLiteDatabase.insert(db_table,null,cv);
    }
    public ArrayList<String> getCategories()
    {
        String[] coloumn=new String[]{categories};
        Cursor c= sqLiteDatabase.query(db_table,coloumn,null,null,null,null,null);
        ArrayList<String> categoryList=new ArrayList<>();
        int categoryIndex=c.getColumnIndex(categories);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            categoryList.add(c.getString(categoryIndex));
        }
        return categoryList;
    }
    public ArrayList<String> getIdeas(String category)
    {
        String[] coloumn=new String[]{ideas,key_id};
        Cursor c= sqLiteDatabase.query(db_table,coloumn,categories+" =?",new String[]{category},
                null,null,null);

        ArrayList<String> ideasList=new ArrayList<>();
        int ideaIndex=c.getColumnIndex(ideas);
        int id=c.getColumnIndex(key_id);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            ideasList.add(c.getString(id)+". "+c.getString(ideaIndex));
        }
        return ideasList;
    }
    public long deleteCategory(String name)
    {
        return sqLiteDatabase.delete(db_table,categories+" =? ",new String[]{name});
    }
    public long deleteIdea(String name)
    {
        return sqLiteDatabase.delete(db_table,ideas+" =? ",new String[]{name});
    }
    public String getInstruction(String idea)
    {
        String instruction="";
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT instructions FROM tabl where ideas= '"+idea+"'", null);
        cursor.moveToFirst();

        instruction=cursor.getString(0);
        return instruction;
    }
    public long updateCategory(String oldname,String newName){
        ContentValues cv= new ContentValues();
        //String id = "SELECT key_id FROM User WHERE name = " + oldname;
        cv.put("categories",newName);
        //cv.put("key_id",id);
        return sqLiteDatabase.update(db_table,cv,oldname+" =? ",new String[]{oldname});
    }
}
