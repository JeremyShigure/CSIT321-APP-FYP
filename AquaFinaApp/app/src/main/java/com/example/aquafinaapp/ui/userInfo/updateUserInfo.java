package com.example.aquafinaapp.ui.userInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aquafinaapp.MainActivity;
import com.example.aquafinaapp.R;

public class updateUserInfo extends AppCompatActivity {

    EditText etContactNo, etEmail, etAddress, etPassword, etConfirmPassword;
    Button returnHomeButton;

    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        password = intent.getStringExtra("password");

        etContactNo = (EditText) findViewById(R.id.etUserName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        //etAddress = (EditText) findViewById(R.id.etAddress);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);

        returnHomeButton = (Button) findViewById(R.id.returnHomeButton);
        returnHomeButton.setOnClickListener(updateAndReturnHome);



    }


    private View.OnClickListener updateAndReturnHome = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent updateAndReturnHomeActivity = new Intent(updateUserInfo.this, MainActivity.class);
            updateAndReturnHomeActivity.putExtra("userName", userName);
            updateAndReturnHomeActivity.putExtra("password", password);
            startActivity(updateAndReturnHomeActivity);
        }
    };



}