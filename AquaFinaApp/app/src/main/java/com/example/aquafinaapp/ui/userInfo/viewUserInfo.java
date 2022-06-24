package com.example.aquafinaapp.ui.userInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aquafinaapp.Controller.customerController;
import com.example.aquafinaapp.MainActivity;
import com.example.aquafinaapp.R;

public class viewUserInfo extends AppCompatActivity {

    TextView tvShowUserName, tvShowContactNo, tvShowEmail, tvShowRegion, tvShowAddress, tvShowFullName;
    Button returnHomeButton, updateUserInfoButton;

    private String userName;
    private String password;

    private String showTvShowUserName;
    private String showTvShowContactNo;
    private String showTvShowEmail;
    private String showTvShowRegion;
    private String showTvShowAddress;
    private String showTvShowFullName;

    customerController customerController = new customerController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_info);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        password = intent.getStringExtra("password");

        tvShowUserName = findViewById(R.id.tvShowUserName);
        tvShowContactNo = findViewById(R.id.tvShowContactNo);
        tvShowEmail = findViewById(R.id.tvShowEmail);
        tvShowRegion = findViewById(R.id.tvShowRegion);
        tvShowAddress = findViewById(R.id.tvShowAddress);
        tvShowFullName = findViewById(R.id.tvShowFullName);

        returnHomeButton = findViewById(R.id.returnHomeButton);
        returnHomeButton.setOnClickListener(returnHome);


        updateUserInfoButton = (Button)findViewById(R.id.updateUserInfoButton);
        updateUserInfoButton.setOnClickListener(updateUserInfo);

        Cursor res = customerController.viewUserInfo(userName, password);

        StringBuffer buffer = new StringBuffer();

        if(res!=null && res.getCount() > 0)
        {
            if (res.moveToFirst()) {
                do {
                    showTvShowUserName = res.getString(0);
                    showTvShowContactNo = res.getString(1);
                    showTvShowEmail = res.getString(2);
                    showTvShowRegion = res.getString(3);
                    showTvShowAddress = res.getString(4);
                    showTvShowFullName = res.getString(5);

                } while (res.moveToNext());
            }
        }

        tvShowUserName.setText(showTvShowUserName);
        tvShowContactNo.setText(showTvShowContactNo);
        tvShowEmail.setText(showTvShowEmail);
        tvShowRegion.setText(showTvShowRegion);
        tvShowAddress.setText(showTvShowAddress);
        tvShowFullName.setText(showTvShowFullName);


    }

    private View.OnClickListener returnHome = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent returnHomeActivity = new Intent(viewUserInfo.this, MainActivity.class);
            returnHomeActivity.putExtra("userName", userName);
            returnHomeActivity.putExtra("password", password);
            startActivity(returnHomeActivity);

        }
    };


    private View.OnClickListener updateUserInfo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent updateUserInfoActivity = new Intent(viewUserInfo.this, updateUserInfo.class);
            updateUserInfoActivity.putExtra("userName", userName);
            updateUserInfoActivity.putExtra("password", password);
            startActivity(updateUserInfoActivity);
        }
    };


}