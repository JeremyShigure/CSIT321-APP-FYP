package com.example.aquafinaapp.ui.userInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aquafinaapp.MainActivity;
import com.example.aquafinaapp.R;

public class updateCustomerOptions extends AppCompatActivity {

    private String userName;
    private String password;

    Button btnUpdateCustomerContactNo, btnUpdateCustomerEmail, btnUpdateCustomerPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cutsomer_options);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        password = intent.getStringExtra("password");

        btnUpdateCustomerContactNo = (Button) findViewById(R.id.btnUpdateCustomerContactNo);
        btnUpdateCustomerEmail = (Button) findViewById(R.id.btnUpdateCustomerEmail);
        btnUpdateCustomerPassword = (Button) findViewById(R.id.btnUpdateCustomerPassword);

        btnUpdateCustomerContactNo.setOnClickListener(updateContactNo);
        btnUpdateCustomerEmail.setOnClickListener(updateEmail);
        btnUpdateCustomerPassword.setOnClickListener(updatePassword);
    }


    private View.OnClickListener updateContactNo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent updateCustomerContactNumber = new Intent(updateCustomerOptions.this, updateCustomerContact.class);
            updateCustomerContactNumber.putExtra("userName", userName);
            updateCustomerContactNumber.putExtra("password", password);
            startActivity(updateCustomerContactNumber);
        }
    };

    private View.OnClickListener updateEmail = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent updateCustomerEmail = new Intent(updateCustomerOptions.this, updateCustomerEmail.class);
            updateCustomerEmail.putExtra("userName", userName);
            updateCustomerEmail.putExtra("password", password);
            startActivity(updateCustomerEmail);
        }
    };

    private View.OnClickListener updatePassword = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent updateCustomerPassword = new Intent(updateCustomerOptions.this, updateCustomerPassword.class);
            updateCustomerPassword.putExtra("userName", userName);
            updateCustomerPassword.putExtra("password", password);
            startActivity(updateCustomerPassword);
        }
    };


}