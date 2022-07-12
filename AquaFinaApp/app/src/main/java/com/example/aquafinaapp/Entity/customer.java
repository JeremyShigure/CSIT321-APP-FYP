package com.example.aquafinaapp.Entity;

import android.content.ContentValues;
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

    public customer(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBNAME, factory, version); //Prevents it from creating an SQL .db file until you set a name to a value instead of null
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

    public SQLiteDatabase openDatabase(){
//        String path = DBLOCATION + DBNAME;
//        mDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
//        return mDatabase;

        String dbPath = DBLOCATION + DBNAME;
        if (mDatabase != null && mDatabase.isOpen()) {
            return mDatabase;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
        return mDatabase;
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

    public Cursor viewUserInfo(String userName, String password) {
        SQLiteDatabase db = openDatabase();

        String[] selectionArgs = new String[]{userName, password};

        //testing
//        System.out.println("uuuuuuuuuuuuuuuuuuuuuussssssssssssssssssssseeeeeeeeeeeeeeeeeeerrrrrrrrrrr" + userName);
//        System.out.println("ppppppppppaaaaaaaaaaaaaassssssssssssssssssswwwwwwwwwwwwwddddddddddd" +password);
        Cursor res = db.rawQuery("SELECT cUsername, cContact, cEmail, cRegion, cAddress, cName FROM " + "customer" + " WHERE cUsername =? AND cPassword =?", selectionArgs);
        return res;
    }


    public boolean updateCustomerInfo(String cUsername, String cPassword, String cContact, String cEmail, String newPassword) {

        SQLiteDatabase db = openDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("cContact", cContact);
        contentValues.put("cEmail", cEmail);
        contentValues.put("cPassword", newPassword);

        //If insert fails, its gonna return -1 to us

        long result = db.update("customer", contentValues, "cUsername = ? and cPassword = ?", new String[]{cUsername, cPassword});
        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }


}
