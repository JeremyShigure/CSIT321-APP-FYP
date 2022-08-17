package com.example.aquafinaapp.ui.userInfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.aquafinaapp.MainActivity;
import com.example.aquafinaapp.R;

public class aboutUs extends AppCompatActivity {

    Button returnHomeButton;
    TextView tvOfficeNum, tvOfficeEmail, tvOfficeAddress;

    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        password = intent.getStringExtra("password");

        returnHomeButton = (Button) findViewById(R.id.returnHomeButton);
        returnHomeButton.setOnClickListener(returnHome);

        tvOfficeNum = (TextView) findViewById(R.id.tvOfficeNum);
        tvOfficeNum.setOnClickListener(phoneCall);

        tvOfficeEmail = (TextView) findViewById(R.id.tvOfficeEmail);
        tvOfficeEmail.setOnClickListener(email);

        tvOfficeAddress = (TextView) findViewById(R.id.tvOfficeAddress);
        tvOfficeAddress.setOnClickListener(address);
    }

    private View.OnClickListener returnHome = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent returnHomeActivity = new Intent(aboutUs.this, MainActivity.class);

            userInfoFragment myFrag = new userInfoFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Bundle bundle = new Bundle();
            bundle.putString("userName", userName);
            bundle.putString("password", password);

            myFrag.setArguments(bundle);

            myFrag.setArguments(bundle);
            returnHomeActivity.putExtras(bundle);

            returnHomeActivity.putExtra("userName", userName);
            returnHomeActivity.putExtra("password", password);
            startActivity(returnHomeActivity);
        }
    };

    private View.OnClickListener phoneCall = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent phoneCallActivity = new Intent(Intent.ACTION_DIAL);
            phoneCallActivity.setData(Uri.parse("tel:68302991"));

            phoneCallActivity.putExtra("userName", userName);
            phoneCallActivity.putExtra("password", password);
            startActivity(phoneCallActivity);
        }
    };

    private View.OnClickListener email = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent emailActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "aquafina_supply@gmail.com"));
            emailActivity.putExtra("userName", userName);
            emailActivity.putExtra("password", password);
            startActivity(emailActivity);
        }
    };

    private View.OnClickListener address = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent addressActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:?q=110 Orchard Rd S520419 14-10"));
            addressActivity.putExtra("userName", userName);
            addressActivity.putExtra("password", password);
            addressActivity.setPackage("com.google.android.apps.maps");
            startActivity(addressActivity);
        }
    };
}