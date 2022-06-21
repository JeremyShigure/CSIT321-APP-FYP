package com.example.aquafinaapp.ui.loginPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aquafinaapp.Controller.customerController;
import com.example.aquafinaapp.Entity.customer;
import com.example.aquafinaapp.MainActivity;
import com.example.aquafinaapp.R;
import com.example.aquafinaapp.common.preferences;

import java.io.File;

public class loginPage extends AppCompatActivity {

    private Button btnLogin;
    private EditText etUserName, etPassword;

    customer cus = new customer(this);

    customerController customerController = new customerController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        etUserName = (EditText)findViewById(R.id.etUserName);
        etPassword = (EditText)findViewById(R.id.etPassword);

        btnLogin = (Button)findViewById(R.id.loginButton);

        btnLogin.setOnClickListener(btnLoginListener);

        File database = getApplicationContext().getDatabasePath(customerController.DBNAME);
        if (database.exists() == false) {

            cus.getReadableDatabase();

            if (customerController.copyDatabase(this)) {
                Toast.makeText(this, "Copy database success!!!!!!!", Toast.LENGTH_SHORT);
                System.out.println("Copy database success!!!!!!!");
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT);
                System.out.println("Copy data error");
                return;
            }
        }
    }

    private View.OnClickListener btnLoginListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            String userName = etUserName.getText().toString();
            String password = etPassword.getText().toString();

//            if (userName.isEmpty() || password.isEmpty()) {
            if (userName.isEmpty() || password.isEmpty()) {
                Toast.makeText(loginPage.this, "One or more fields is empty!", Toast.LENGTH_SHORT).show();
            }

            else if (customerController.checkUser(userName, password)) {

                preferences.putLogInStatus(getApplicationContext(), true);
                preferences.putLoggedInUser(getApplicationContext(), userName);

                Intent login = new Intent(loginPage.this, MainActivity.class);
                startActivity(login);
            }
            else {
                Toast.makeText(loginPage.this, "User not found!!", Toast.LENGTH_SHORT).show();
            }
        }
    };

}