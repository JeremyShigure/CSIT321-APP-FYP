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

    public static final String DBNAME = "aquafina.db";
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
//        openDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP DATABASE IF EXISTS" + DBNAME);
//        onCreate(db);
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

    // Check this user (customer) exist in database or not, if yes, then return true, else will return false
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

    // Get all the information including user name, contact number, email, region, address, and name based on the customer details
    public Cursor viewUserInfo(String userName, String password) {
        SQLiteDatabase db = openDatabase();

        String[] selectionArgs = new String[]{userName, password};

        //testing
//        System.out.println("uuuuuuuuuuuuuuuuuuuuuussssssssssssssssssssseeeeeeeeeeeeeeeeeeerrrrrrrrrrr" + userName);
//        System.out.println("ppppppppppaaaaaaaaaaaaaassssssssssssssssssswwwwwwwwwwwwwddddddddddd" +password);
        Cursor res = db.rawQuery("SELECT cUsername, cContact, cEmail, cRegion, cAddress, cName FROM " + "customer" + " WHERE cUsername =? AND cPassword =?", selectionArgs);

//        res.close();

        return res;
    }

    // Update the customer contact number
    public boolean updateCustomerContact(String cUsername, String cPassword, String cContact) {

        SQLiteDatabase db = openDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("cContact", cContact);

        //If update fails, its gonna return -1 to us

        long result = db.update("customer", contentValues, "cUsername = ? and cPassword = ?", new String[]{cUsername, cPassword});
        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    // Update the customer email
    public boolean updateCustomerEmail(String cUsername, String cPassword, String cEmail) {

        SQLiteDatabase db = openDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("cEmail", cEmail);

        //If update fails, its gonna return -1 to us

        long result = db.update("customer", contentValues, "cUsername = ? and cPassword = ?", new String[]{cUsername, cPassword});

        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    // Update the customer password
    public boolean updateCustomerPassword(String cUsername, String cPassword, String newPassword) {

        SQLiteDatabase db = openDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("cPassword", newPassword);

        //If update fails, its gonna return -1 to us

        long result = db.update("customer", contentValues, "cUsername = ? and cPassword = ?", new String[]{cUsername, cPassword});

        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    // Get the start date (which will be converted to month), water usage, and house type based on the customer and limit to 6 as the bar chart will only show past 6 months of data
    public Cursor getMeterInfo(String userName) {
        SQLiteDatabase db = openDatabase();

        String[] selectionArgs = new String[]{userName};

//        String query = "SELECT startDate FROM waterUsage LIMIT 6";
//        String query = "SELECT wu.startDate, wu.endDate, wu.wUsage from waterUsage wu JOIN customer c ON wu.cID = c.cID WHERE customer.cUsername =?";
//        String query = "SELECT wu.startDate, wu.wUsage from waterUsage wu JOIN customer c ON wu.cID = c.cID WHERE c.cUsername =?";
//        String query = "SELECT startDate, wUsage, cHouseType from waterUsage, customer WHERE waterUsage.cID = customer.cID AND customer.cUsername =? LIMIT 6";
        String query = "SELECT wu.startDate, wu.wUsage, c.cHouseType from waterUsage wu JOIN customer c ON wu.cID = c.cID WHERE c.cUsername =? LIMIT 6";

        //testing
//        System.out.println("uuuuuuuuuuuuuuuuuuuuuussssssssssssssssssssseeeeeeeeeeeeeeeeeeerrrrrrrrrrr" + userName);
//        System.out.println("ppppppppppaaaaaaaaaaaaaassssssssssssssssssswwwwwwwwwwwwwddddddddddd" +password);
//        SELECT wu.startDate, wu.endDate, wu.wUsage from waterUsage wu JOIN customer c ON wu.cID = c.cID where c.cUsername = 'cus1'
//        Cursor res = db.rawQuery("SELECT cUsername, cContact, cEmail, cRegion, cAddress, cName FROM customer WHERE cUsername =? AND cPassword =?", selectionArgs);
//        Cursor res = db.rawQuery("SELECT waterUsage.startDate FROM waterUsage JOIN customer ON waterUsage.cID = customer.cID WHERE customer.cUsername =?", selectionArgs);
        Cursor res = db.rawQuery(query, selectionArgs);

//        res.close();

        return res;
    }


//    public Cursor viewWaterUsage(String userName) {
//        SQLiteDatabase db = openDatabase();
//
//        String[] columns = {"*"};
//
//        String selection = "cUsername=?";
//
//        String[] selectionArgs = {userName};
//
//        //testing
////        System.out.println("uuuuuuuuuuuuuuuuuuuuuussssssssssssssssssssseeeeeeeeeeeeeeeeeeerrrrrrrrrrr" + userName);
////        System.out.println("ppppppppppaaaaaaaaaaaaaassssssssssssssssssswwwwwwwwwwwwwddddddddddd" +password);
////        SELECT wu.startDate, wu.endDate, wu.wUsage from waterUsage wu JOIN customer c ON wu.cID = c.cID where c.cUsername = 'cus1'
////        Cursor res = db.rawQuery("SELECT cUsername, cContact, cEmail, cRegion, cAddress, cName FROM customer WHERE cUsername =? AND cPassword =?", selectionArgs);
//        Cursor res = db.query("allWaterUsage", columns, selection, selectionArgs, null, null, null);
//        return res;
//    }


    // Get the predicted average household water usage based on the house type of the customer and current month
    public Cursor viewAveWaterUsage(String houseType, String month) {
        SQLiteDatabase db = openDatabase();

        String[] selectionArgs = new String[]{houseType, month};

        String query = "SELECT cHouseType, month, predictUsage from waterUsagePrediction WHERE cHouseType =? AND month =?";

        Cursor res = db.rawQuery(query, selectionArgs);

//        res.close();

        return res;
    }

    // Get all the FAQ words
    public Cursor getFAQInfo() {
        SQLiteDatabase db = openDatabase();

        String query = "SELECT * from FAQ";

        Cursor res = db.rawQuery(query, null);

        return res;
    }


    // billDetails
    public Cursor getInvoiceInfo(String date, String totalCost, String userName) {
        SQLiteDatabase db = openDatabase();

        String[] selectionArgs = new String[]{date, totalCost, userName};

        String query = "SELECT bd.accountID, bd.invoiceID, bd.startDate, bd.endDate, bd.nettCost, bd.consTax, bd.borneFee, bd.subTotal, bd.GST, bd.totalCost, bd.dateIssued, bd.dueDate FROM billingDetail bd JOIN customer c ON bd.cID = c.cID WHERE bd.startDate=? AND bd.totalCost=? AND c.cUsername=? limit 6";

//            SELECT bd.invoiceID, bd.accountID, bd.startDate, bd.endDate, bd.netCost, bd.consTax, bd.GST, bd.totalCost FROM billingDetails db JOIN customer c ON db.cID = c.cID WHERE c.userName=?

        Cursor res = db.rawQuery(query, selectionArgs);

//        res.close();

        return res;
    }


    // Get all the water saving tips
    public Cursor getWaterSavingTips() {
        SQLiteDatabase db = openDatabase();

        String query = "SELECT * from waterSavingTips";

        Cursor res = db.rawQuery(query, null);

        return res;
    }


    // viewPaymentDetails.java
    public Cursor getPaymentDetails(String userName) {
        SQLiteDatabase db = openDatabase();

        String[] selectionArgs = new String[]{userName};

        String query = "SELECT bd.startDate, bd.totalCost FROM billingDetail bd JOIN customer c ON bd.cID = c.cID WHERE c.cUsername = ? LIMIT 6";
//            SELECT bd.invoiceID, bd.accountID, bd.startDate, bd.endDate, bd.netCost, bd.consTax, bd.GST, bd.totalCost FROM billingDetails db JOIN customer c ON db.cID = c.cID WHERE c.userName=?

        Cursor res = db.rawQuery(query, selectionArgs);

//        res.close();

        return res;
    }


    // viewPaymentDetails.java
    public Cursor getNewestPaymentDetails(String userName) {
        SQLiteDatabase db = openDatabase();

        String[] selectionArgs = new String[]{userName};

        String query = "SELECT bd.invoiceID, bd.totalCost FROM billingDetail bd JOIN customer c ON bd.cID = c.cID WHERE c.cUsername=? ORDER BY bd.invoiceID DESC LIMIT 1";
//            SELECT bd.invoiceID, bd.accountID, bd.startDate, bd.endDate, bd.netCost, bd.consTax, bd.GST, bd.totalCost FROM billingDetails db JOIN customer c ON db.cID = c.cID WHERE c.userName=?

        Cursor res = db.rawQuery(query, selectionArgs);

//        res.close();

        return res;
    }

    // Get latest month of bill details using invoiceID and totalCost
    public Cursor getNewestInvoiceInfo(String invoiceID, String totalCost, String userName) {
        SQLiteDatabase db = openDatabase();

        String[] selectionArgs = new String[]{invoiceID, totalCost, userName};

        String query = "SELECT bd.accountID, bd.invoiceID, bd.startDate, bd.endDate, bd.nettCost, bd.consTax, bd.borneFee, bd.subTotal, bd.GST, bd.totalCost, bd.dateIssued, bd.dueDate FROM billingDetail bd JOIN customer c ON bd.cID = c.cID WHERE bd.invoiceID=? AND bd.totalCost=? AND c.cUsername=? limit 6";

//            SELECT bd.invoiceID, bd.accountID, bd.startDate, bd.endDate, bd.netCost, bd.consTax, bd.GST, bd.totalCost FROM billingDetails db JOIN customer c ON db.cID = c.cID WHERE c.userName=?

        Cursor res = db.rawQuery(query, selectionArgs);

//        res.close();

        return res;
    }

}
