package com.example.aquafinaapp.ui.userInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
//            Intent returnHomeActivity = new Intent(aboutUs.this, getClass());




            userInfoFragment myFrag = new userInfoFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Bundle bundle = new Bundle();
            bundle.putString("userName", userName);
            bundle.putString("password", password);

            myFrag.setArguments(bundle);

            fragmentTransaction.replace(R.id.userInfoContainerFragment, myFrag).addToBackStack(userInfoFragment.class.getSimpleName()).commit();




//            returnHomeActivity.putExtra("userName", userName);
//            returnHomeActivity.putExtra("password", password);
//            startActivity(returnHomeActivity);

        }
    };

}