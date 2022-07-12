package com.example.aquafinaapp.ui.userInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aquafinaapp.Controller.customerController;
import com.example.aquafinaapp.MainActivity;
import com.example.aquafinaapp.R;

public class updateUserInfo extends AppCompatActivity {

    customerController customerController = new customerController();

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
            if (etContactNo.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()|| etConfirmPassword.getText().toString().isEmpty()) {
                Toast.makeText(updateUserInfo.this, "One or more fields is empty!", Toast.LENGTH_SHORT).show();
            }
            else if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                Toast.makeText(updateUserInfo.this, "Please make sure passwords are same!", Toast.LENGTH_SHORT).show();
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
                Toast.makeText(updateUserInfo.this, "Please make sure \nemail format is correct!", Toast.LENGTH_SHORT).show();
            }
            else {
                boolean check = customerController.updateCustomerInfo(userName, password, etContactNo.getText().toString(), etEmail.getText().toString(), etConfirmPassword.getText().toString());

                if (check) {
                    Toast.makeText(updateUserInfo.this, "Update successfully \nand return to home page", Toast.LENGTH_SHORT).show();
                    Intent updateAndReturnHomeActivity = new Intent(updateUserInfo.this, MainActivity.class);
                    updateAndReturnHomeActivity.putExtra("userName", userName);
                    updateAndReturnHomeActivity.putExtra("password", etConfirmPassword.getText().toString());
                    startActivity(updateAndReturnHomeActivity);
                }
                else {
                    Toast.makeText(updateUserInfo.this, "Update Failed!", Toast.LENGTH_SHORT).show();
                }
            }

//            Intent updateAndReturnHomeActivity = new Intent(updateUserInfo.this, MainActivity.class);
//            updateAndReturnHomeActivity.putExtra("userName", userName);
//            updateAndReturnHomeActivity.putExtra("password", password);
//            startActivity(updateAndReturnHomeActivity);
        }
    };

}