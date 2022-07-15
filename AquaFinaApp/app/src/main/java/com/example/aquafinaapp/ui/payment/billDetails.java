package com.example.aquafinaapp.ui.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aquafinaapp.MainActivity;
import com.example.aquafinaapp.R;
import com.example.aquafinaapp.ui.paymentSuccessful.viewPaymentDetails;

public class billDetails extends AppCompatActivity {

    Button returnHomeButton;

    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        password = intent.getStringExtra("password");

        returnHomeButton = (Button) findViewById(R.id.returnHomeButton);
        returnHomeButton.setOnClickListener(returnHome);
    }

    private View.OnClickListener returnHome = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent returnHomeActivity = new Intent (billDetails.this, MainActivity.class);
            returnHomeActivity.putExtra("userName", userName);
            returnHomeActivity.putExtra("password", password);
            startActivity(returnHomeActivity);
        }
    };
}