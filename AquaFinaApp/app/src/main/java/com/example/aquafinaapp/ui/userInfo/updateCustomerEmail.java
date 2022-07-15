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

public class updateCustomerEmail extends AppCompatActivity {

    customerController customerController = new customerController();

    TextView tvHintsWords, tvEmailHints, tvEmail;
    EditText etEmail;
    Button returnHomeButton;

    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer_email);

//        tvHintsWords = (TextView)findViewById(R.id.tvHintsWords);
//        tvEmailHints = (TextView)findViewById(R.id.tvEmailHints);
        tvEmail = (TextView)findViewById(R.id.tvEmail);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        password = intent.getStringExtra("password");

        etEmail = (EditText) findViewById(R.id.etEmail);

        returnHomeButton = (Button) findViewById(R.id.returnHomeButton);
        returnHomeButton.setOnClickListener(updateAndReturnHome);

    }


    private View.OnClickListener updateAndReturnHome = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (etEmail.getText().toString().isEmpty()) {
                Toast.makeText(updateCustomerEmail.this, "One or more fields is empty!", Toast.LENGTH_SHORT).show();
            }
//            else if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
//                Toast.makeText(updateCustomerEmail.this, "Please make sure passwords are same!", Toast.LENGTH_SHORT).show();
//            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
                Toast.makeText(updateCustomerEmail.this, "Please make sure \nemail format is correct!", Toast.LENGTH_SHORT).show();
            }
            else {
                boolean check = customerController.updateCustomerEmail(userName, password, etEmail.getText().toString());

                if (check) {
                    Toast.makeText(updateCustomerEmail.this, "Update email successfully \nand return to home page", Toast.LENGTH_SHORT).show();
                    Intent updateAndReturnHomeActivity = new Intent(updateCustomerEmail.this, MainActivity.class);
                    updateAndReturnHomeActivity.putExtra("userName", userName);
                    updateAndReturnHomeActivity.putExtra("password", password);
                    startActivity(updateAndReturnHomeActivity);
                }
                else {
                    Toast.makeText(updateCustomerEmail.this, "Update Failed!", Toast.LENGTH_SHORT).show();
                }
            }

//            Intent updateAndReturnHomeActivity = new Intent(updateUserInfo.this, MainActivity.class);
//            updateAndReturnHomeActivity.putExtra("userName", userName);
//            updateAndReturnHomeActivity.putExtra("password", password);
//            startActivity(updateAndReturnHomeActivity);
        }
    };

}