package com.example.aquafinaapp.ui.userInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aquafinaapp.Controller.customerController;
import com.example.aquafinaapp.MainActivity;
import com.example.aquafinaapp.R;

public class updateCustomerPassword extends AppCompatActivity {

    customerController customerController = new customerController();

    TextView tvHintsWords, tvPasswordHints, tvEmail;
    EditText etPassword, etConfirmPassword;
    Button returnHomeButton;

    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer_password);

//        tvHintsWords = (TextView)findViewById(R.id.tvHintsWords);
//        tvPasswordHints = (TextView)findViewById(R.id.tvPasswordHints);
        tvEmail = (TextView)findViewById(R.id.tvEmail);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        password = intent.getStringExtra("password");

        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);

        returnHomeButton = (Button) findViewById(R.id.returnHomeButton);
        returnHomeButton.setOnClickListener(updateAndReturnHome);

    }


    private View.OnClickListener updateAndReturnHome = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (etPassword.getText().toString().isEmpty() || etConfirmPassword.getText().toString().isEmpty()) {
                Toast.makeText(updateCustomerPassword.this, "One or more fields is empty!", Toast.LENGTH_SHORT).show();
            }
            else if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                Toast.makeText(updateCustomerPassword.this, "Please make sure passwords are same!", Toast.LENGTH_SHORT).show();
            }
//            else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
//                Toast.makeText(updateCustomerPassword.this, "Please make sure \nemail format is correct!", Toast.LENGTH_SHORT).show();
//            }
            else {
                boolean check = customerController.updateCustomerPassword(userName, password, etConfirmPassword.getText().toString());

                if (check) {
                    Toast.makeText(updateCustomerPassword.this, "Update password successfully \nand return to home page", Toast.LENGTH_SHORT).show();
                    Intent updateAndReturnHomeActivity = new Intent(updateCustomerPassword.this, MainActivity.class);
                    updateAndReturnHomeActivity.putExtra("userName", userName);
                    updateAndReturnHomeActivity.putExtra("password", etConfirmPassword.getText().toString());
                    startActivity(updateAndReturnHomeActivity);
                }
                else {
                    Toast.makeText(updateCustomerPassword.this, "Update Failed!", Toast.LENGTH_SHORT).show();
                }
            }

//            Intent updateAndReturnHomeActivity = new Intent(updateUserInfo.this, MainActivity.class);
//            updateAndReturnHomeActivity.putExtra("userName", userName);
//            updateAndReturnHomeActivity.putExtra("password", password);
//            startActivity(updateAndReturnHomeActivity);
        }
    };

}