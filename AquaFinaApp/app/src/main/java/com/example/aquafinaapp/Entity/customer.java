package com.example.aquafinaapp.Entity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class customer extends SQLiteOpenHelper {
    public static final String DBNAME = "aquafinaDB.db";
    public static final String DBLOCATION = "/data/data/com.example.aquafinaapp/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public customer(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase openDatabase(){
        String path = DBLOCATION + DBNAME;
        mDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        return mDatabase;
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public boolean checkUser(String username, String password){
        String[] columns = {"cUsername, cPassword"};
        mDatabase = openDatabase();

        String selection = "cUsername=? and cPassword=? ";
        String[] selectionArgs = {username, password};

        Cursor cursor = mDatabase.query("customer", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();
        close();

        if(count > 0) {
            return true;
        } else {
            return false;
        }
    }

}
