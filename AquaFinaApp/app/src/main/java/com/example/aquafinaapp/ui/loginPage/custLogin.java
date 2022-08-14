package com.example.aquafinaapp.ui.loginPage;


import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aquafinaapp.Controller.customerController;
import com.example.aquafinaapp.Entity.customer;
import com.example.aquafinaapp.MainActivity;
import com.example.aquafinaapp.R;
import com.example.aquafinaapp.common.preferences;
import com.example.aquafinaapp.ui.userInfo.userInfoFragment;
import com.example.aquafinaapp.ui.waterSavingTips.waterSavingTips;

import java.io.File;
import java.sql.Connection;
import java.util.concurrent.Executor;

public class custLogin extends AppCompatActivity {

    private Button btnLogin;
    private EditText etUserName, etPassword;
    private TextView tvWaterSavingTips;

    String userName;
    String password;

    ImageView imFingerprint;
    private static final int REQUEST_CODE = 101010;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    customer cus = new customer(this);

    customerController customerController = new customerController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        etUserName = (EditText)findViewById(R.id.etUserName);
        etPassword = (EditText)findViewById(R.id.etPassword);

        tvWaterSavingTips = (TextView) findViewById(R.id.tvWaterSavingTips);


        // Fingerprint login here
        // ---------------------------------------------------------------------------------------
        imFingerprint = (ImageView) findViewById(R.id.imFingerprint);

        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate(BIOMETRIC_STRONG | DEVICE_CREDENTIAL)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Log.e("MY_APP_TAG", "Fingerprint sensor not available.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Log.e("MY_APP_TAG", "Biometric features are busy.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                // Prompts the user to create credentials that your app accepts.
                final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
                enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG | DEVICE_CREDENTIAL);
                startActivityForResult(enrollIntent, REQUEST_CODE);
                break;
        }
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(custLogin.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                                "Authentication error: " + errString, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

//                userName = etUserName.getText().toString();
//                password = etPassword.getText().toString();

                userName = "cus1";
                password = "pw123";

                customerController.checkUser(userName, password);

                preferences.putLogInStatus(getApplicationContext(), true);
                preferences.putLoggedInUser(getApplicationContext(), userName.toString());

                Intent login = new Intent(custLogin.this, MainActivity.class);
//                login.putExtra("userName", etUserName.getText().toString());
//                login.putExtra("password", etPassword.getText().toString());

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putString("userName", userName);
                bundle.putString("password", password);

                userInfoFragment userInfoFrag = new userInfoFragment();

                userInfoFrag.setArguments(bundle);
//                fragmentTransaction.replace(R.id.).commit();

                login.putExtras(bundle);
                startActivity(login);

                Toast.makeText(getApplicationContext(),
                        "Authentication succeeded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed",
                                Toast.LENGTH_SHORT)
                        .show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build();

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.

//        Button biometricLoginButton = findViewById(R.id.biometric_login);

        imFingerprint.setOnClickListener(view -> {
            biometricPrompt.authenticate(promptInfo);
        });




        // ---------------------------------------------------------------------------------------

        btnLogin = (Button)findViewById(R.id.loginButton);

        btnLogin.setOnClickListener(btnLoginListener);

        tvWaterSavingTips.setOnClickListener(btnWaterSavingTips);

        File database = getApplicationContext().getDatabasePath(customerController.DBNAME);
        if (database.exists() == false) {

            cus.getReadableDatabase();

            if (customerController.copyDatabase(this)) {
                Toast.makeText(this, "Copy database success!!!!!!!", Toast.LENGTH_SHORT);
                System.out.println("Copy database success!!!!!!!");
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT);
                System.out.println("Copy data error");
                return;
            }
        }
    }

    private View.OnClickListener btnLoginListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            userName = etUserName.getText().toString();
            password = etPassword.getText().toString();

//            if (userName.isEmpty() || password.isEmpty()) {
            if (userName.isEmpty() || password.isEmpty()) {
                Toast.makeText(custLogin.this, "One or more fields is empty!", Toast.LENGTH_SHORT).show();
            }

            else if (customerController.checkUser(userName, password)) {

                preferences.putLogInStatus(getApplicationContext(), true);
                preferences.putLoggedInUser(getApplicationContext(), userName.toString());

                Intent login = new Intent(custLogin.this, MainActivity.class);
//                login.putExtra("userName", etUserName.getText().toString());
//                login.putExtra("password", etPassword.getText().toString());

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putString("userName", etUserName.getText().toString());
                bundle.putString("password", etPassword.getText().toString());

                userInfoFragment userInfoFrag = new userInfoFragment();

                userInfoFrag.setArguments(bundle);
//                fragmentTransaction.replace(R.id.).commit();

                login.putExtras(bundle);
                startActivity(login);
            }
            else {
                Toast.makeText(custLogin.this, "User not found!!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private View.OnClickListener btnWaterSavingTips = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent waterSavingTipsActivity = new Intent(custLogin.this, waterSavingTips.class);
            startActivity(waterSavingTipsActivity);
        }
    };

//
//    private View.OnClickListener btnLoginListener = new View.OnClickListener() {
//
//        @Override
//        public void onClick(View view) {
//
//            String userName = etUserName.getText().toString();
//            String password = etPassword.getText().toString();
//
//            String loginTypeIn = userName + password;
//            System.out.println("loginTypeIn: " + loginTypeIn);
//
////            String storeLogin = cusCloud.getCustomerLogin(userName, password);
////            System.out.println("storeLogin: " + storeLogin);
//
//
////            if (userName.isEmpty() || password.isEmpty()) {
//            if (userName.isEmpty() || password.isEmpty()) {
//                Toast.makeText(loginPage.this, "One or more fields is empty!", Toast.LENGTH_SHORT).show();
//            }
//            else if (cusCloud.getCustomerLogin(userName, password)) {
//
//                preferences.putLogInStatus(getApplicationContext(), true);
//                preferences.putLoggedInUser(getApplicationContext(), userName.toString());
//
//                Intent login = new Intent(loginPage.this, MainActivity.class);
////                login.putExtra("userName", etUserName.getText().toString());
////                login.putExtra("password", etPassword.getText().toString());
//
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                Bundle bundle = new Bundle();
//                bundle.putString("userName", etUserName.getText().toString());
//                bundle.putString("password", etPassword.getText().toString());
//
//                userInfoFragment userInfoFrag = new userInfoFragment();
//
//                userInfoFrag.setArguments(bundle);
////                fragmentTransaction.replace(R.id.).commit();
//
//                login.putExtras(bundle);
//                startActivity(login);
//            }
//            else {
//                Toast.makeText(loginPage.this, "User not found!!", Toast.LENGTH_SHORT).show();
//            }
//        }
//    };


}