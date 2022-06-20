package com.example.aquafinaapp.Entity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class customer extends SQLiteOpenHelper {

    public static final String DBNAME = "aquafinaDB.db";
    public static final String DBLOCATION = "/data/data/com.example.aquafinaapp/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;


    int cID;
    String cUsername;
    String cPassword;
    String cName;
    int cContact;
    String cAddress;
    String cEmail;
    String cBankAccount;
    String cRegion;
    int wmID;

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public String getcUsername() {
        return cUsername;
    }

    public void setcUsername(String cUsername) {
        this.cUsername = cUsername;
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getcContact() {
        return cContact;
    }

    public void setcContact(int cContact) {
        this.cContact = cContact;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getcBankAccount() {
        return cBankAccount;
    }

    public void setcBankAccount(String cBankAccount) {
        this.cBankAccount = cBankAccount;
    }

    public String getcRegion() {
        return cRegion;
    }

    public void setcRegion(String cRegion) {
        this.cRegion = cRegion;
    }

    public int getWmID() {
        return wmID;
    }

    public void setWmID(int wmID) {
        this.wmID = wmID;
    }

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

    public void openDatabase(){
//        String path = DBLOCATION + DBNAME;
//        mDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
//        return mDatabase;

        String dbPath = DBLOCATION + DBNAME;
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DBNAME);
            String outFileName = DBLOCATION + DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkUser(String username, String password){
        String[] columns = {"cUsername, cPassword"};
        openDatabase();

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
