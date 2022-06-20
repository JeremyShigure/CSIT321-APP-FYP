package com.example.aquafinaapp.Controller;

import android.content.Context;
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

}
