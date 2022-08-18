package com.example.aquafinaapp.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aquafinaapp.Entity.customer;

public class customerController extends AppCompatActivity {

    customer customer = new customer(customerController.this);

    public String DBNAME = customer.DBNAME;
    public String DBLOCATION = customer.DBLOCATION;

    public boolean checkUser(String userName, String password) {
        boolean isValidate = customer.checkUser(userName, password);

        if (isValidate) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean copyDatabase(Context context) {
        boolean isExist = customer.copyDatabase(context);

        if (isExist) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean updateCustomerContact(String userName, String password, String contact) {
        boolean isValidate = customer.updateCustomerContact(userName, password, contact);

        if (isValidate) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean updateCustomerEmail(String userName, String password, String email) {
        boolean isValidate = customer.updateCustomerEmail(userName, password, email);

        if (isValidate) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean updateCustomerPassword(String userName, String password, String newPassword) {
        boolean isValidate = customer.updateCustomerPassword(userName, password, newPassword);

        if (isValidate) {
            return true;
        }
        else {
            return false;
        }
    }

    public Cursor viewUserInfo(String userName, String password) {
        Cursor res = customer.viewUserInfo(userName, password);

        return res;
    }

    public Cursor getMeterInfo(String userName) {
        Cursor res = customer.getMeterInfo(userName);

        return res;
    }

    public Cursor viewAveWaterUsage(String houseType, String month) {
        Cursor res = customer.viewAveWaterUsage(houseType, month);

        return res;
    }


    public Cursor getFAQInfo() {
        Cursor res = customer.getFAQInfo();

        return res;
    }

    // get all the details of customer invoice
    public Cursor getInvoiceInfo(String date, String totalCost, String userName) {
        Cursor res = customer.getInvoiceInfo(date, totalCost, userName);

        return res;
    }


    public Cursor getWaterSavingTips() {
        Cursor res = customer.getWaterSavingTips();

        return res;
    }


    public Cursor getPaymentDetails(String userName) {
        Cursor res = customer.getPaymentDetails(userName);

        return res;
    }


    public Cursor getNewestPaymentDetails(String userName) {
        Cursor res = customer.getNewestPaymentDetails(userName);

        return res;
    }


    public Cursor getNewestInvoiceInfo(String invoiceID, String totalCost, String userName) {
        Cursor res = customer.getNewestInvoiceInfo(invoiceID, totalCost, userName);

        return res;
    }

}
