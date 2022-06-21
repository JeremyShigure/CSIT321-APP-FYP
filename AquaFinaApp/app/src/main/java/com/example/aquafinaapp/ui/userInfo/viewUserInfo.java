package com.example.aquafinaapp.ui.userInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aquafinaapp.MainActivity;
import com.example.aquafinaapp.R;

public class viewUserInfo extends AppCompatActivity {

    TextView tvShowUserName, tvShowContactNo, tvShowEmail, tvShowRegion, tvShowAddress;
    Button returnHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_info);

        tvShowUserName = findViewById(R.id.tvShowUserName);
        tvShowContactNo = findViewById(R.id.tvShowContactNo);
        tvShowEmail = findViewById(R.id.tvShowEmail);
        tvShowRegion = findViewById(R.id.tvShowRegion);
        tvShowAddress = findViewById(R.id.tvShowAddress);

        returnHomeButton = findViewById(R.id.returnHomeButton);
        returnHomeButton.setOnClickListener(returnHome);







    }

    private View.OnClickListener returnHome = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent returnHomeActivity = new Intent(viewUserInfo.this, MainActivity.class);
            startActivity(returnHomeActivity);

        }
    };


}