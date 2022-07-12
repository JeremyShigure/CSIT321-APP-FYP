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

    public Cursor viewUserInfo(String userName, String password) {
        Cursor res = customer.viewUserInfo(userName, password);
        return res;
    }

    public boolean updateCustomerInfo(String cUsername, String cPassword, String cContact, String cEmail, String newPassword) {
        boolean isValidate = customer.updateCustomerInfo(cUsername, cPassword, cContact, cEmail, newPassword);

        if (isValidate) {
            return true;
        }
        else {
            return false;
        }
    }

}
