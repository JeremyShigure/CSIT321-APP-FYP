package com.example.aquafinaapp.ui.userInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aquafinaapp.Controller.customerController;
import com.example.aquafinaapp.MainActivity;
import com.example.aquafinaapp.R;

public class updateCustomerContact extends AppCompatActivity {

    customerController customerController = new customerController();

    TextView tvHintsWords, tvContactNoHints, tvContactNo;
    EditText etContactNo;
    Button returnHomeButton;

    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer_contact);

        tvHintsWords = (TextView)findViewById(R.id.tvHintsWords);
        tvContactNoHints = (TextView)findViewById(R.id.tvContactNoHints);
        tvContactNo = (TextView)findViewById(R.id.tvContactNo);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        password = intent.getStringExtra("password");

        etContactNo = (EditText) findViewById(R.id.etContactNo);

        returnHomeButton = (Button) findViewById(R.id.returnHomeButton);
        returnHomeButton.setOnClickListener(updateAndReturnHome);

    }


    private View.OnClickListener updateAndReturnHome = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (etContactNo.getText().toString().isEmpty()) {
                Toast.makeText(updateCustomerContact.this, "One or more fields is empty!", Toast.LENGTH_SHORT).show();
            }
            else if (etContactNo.getText().toString().length() != 8) {
                Toast.makeText(updateCustomerContact.this, "Please enter a 8-digit phone number", Toast.LENGTH_SHORT).show();
            }
//            else if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
//                Toast.makeText(updateCustomerEmail.this, "Please make sure passwords are same!", Toast.LENGTH_SHORT).show();
//            }
//            else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
//                Toast.makeText(updateCustomerEmail.this, "Please make sure \nemail format is correct!", Toast.LENGTH_SHORT).show();
//            }
            else {
                boolean check = customerController.updateCustomerContact(userName, password, etContactNo.getText().toString());

                if (check) {
                    Toast.makeText(updateCustomerContact.this, "Update contact number successfully \nand return to home page", Toast.LENGTH_SHORT).show();
                    Intent updateAndReturnHomeActivity = new Intent(updateCustomerContact.this, MainActivity.class);
                    updateAndReturnHomeActivity.putExtra("userName", userName);
                    updateAndReturnHomeActivity.putExtra("password", password);
                    startActivity(updateAndReturnHomeActivity);
                }
                else {
                    Toast.makeText(updateCustomerContact.this, "Update Failed!", Toast.LENGTH_SHORT).show();
                }
            }

//            Intent updateAndReturnHomeActivity = new Intent(updateUserInfo.this, MainActivity.class);
//            updateAndReturnHomeActivity.putExtra("userName", userName);
//            updateAndReturnHomeActivity.putExtra("password", password);
//            startActivity(updateAndReturnHomeActivity);
        }
    };

}