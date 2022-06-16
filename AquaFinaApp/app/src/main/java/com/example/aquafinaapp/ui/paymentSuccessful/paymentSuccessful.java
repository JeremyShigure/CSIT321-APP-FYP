package com.example.aquafinaapp.ui.paymentSuccessful;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aquafinaapp.MainActivity;
import com.example.aquafinaapp.R;

public class paymentSuccessful extends AppCompatActivity {

    Button returnHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_successful);

        returnHomeButton = (Button) findViewById(R.id.returnHomeButton);
        returnHomeButton.setOnClickListener(returnHome);

    }


    private View.OnClickListener returnHome = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent (paymentSuccessful.this, MainActivity.class);
            startActivity(intent);;
        }
    };
}