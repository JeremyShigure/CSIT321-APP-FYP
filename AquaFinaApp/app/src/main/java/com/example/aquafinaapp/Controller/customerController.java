package com.example.aquafinaapp.Controller;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aquafinaapp.Entity.customer;

public class customerController extends AppCompatActivity {

    customer customer = new customer(customerController.this);

    public boolean checkUser(String userName, String password) {
        boolean isValidate = customer.checkUser(userName, password);

        if (isValidate) {
            return true;
        }
        else {
            return false;
        }
    }
}
