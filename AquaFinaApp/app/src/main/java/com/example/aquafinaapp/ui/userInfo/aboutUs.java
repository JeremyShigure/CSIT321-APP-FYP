package com.example.aquafinaapp.ui.userInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aquafinaapp.MainActivity;
import com.example.aquafinaapp.R;

public class aboutUs extends AppCompatActivity {

    Button returnHomeButton;

    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        password = intent.getStringExtra("password");

        returnHomeButton = findViewById(R.id.returnHomeButton);

        returnHomeButton.setOnClickListener(returnHome);

    }

    private View.OnClickListener returnHome = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent returnHomeActivity = new Intent(aboutUs.this, MainActivity.class);
            returnHomeActivity.putExtra("userName", userName);
            returnHomeActivity.putExtra("password", password);
            startActivity(returnHomeActivity);

        }
    };

}