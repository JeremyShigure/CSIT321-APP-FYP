package com.example.aquafinaapp.ui.payment;

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

import java.util.ArrayList;

public class viewPaymentDetails extends AppCompatActivity {

    private String userName;
    private String password;

    private String startDate;
    private String totalCost;

    TextView tvShowDate, tvShowDate2, tvShowDate3, tvShowDate4, tvShowDate5, tvShowDate6, tvBillAmount1, tvBillAmount2, tvBillAmount3, tvBillAmount4, tvBillAmount5, tvBillAmount6;
    Button btnBillDetails1, btnBillDetails2, btnBillDetails3, btnBillDetails4, btnBillDetails5, btnBillDetails6;

    Button returnHomeButton;

    private String date1 = "", date2 = "", date3 = "", date4 = "", date5 = "", date6 = "";
    private String price1 = "", price2 = "", price3 = "", price4 = "", price5 = "", price6 = "";


    private static ArrayList<String> dates = new ArrayList<>();
    private static ArrayList<String> prices = new ArrayList<>();

    customerController cusController = new customerController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        password = intent.getStringExtra("password");


        // Declaring variables
        tvShowDate = (TextView)findViewById(R.id.tvShowDate);
        tvShowDate2 = (TextView)findViewById(R.id.tvShowDate2);
        tvShowDate3 = (TextView)findViewById(R.id.tvShowDate3);
        tvShowDate4 = (TextView)findViewById(R.id.tvShowDate4);
        tvShowDate5 = (TextView)findViewById(R.id.tvShowDate5);
        tvShowDate6 = (TextView)findViewById(R.id.tvShowDate6);

        tvBillAmount1 = (TextView)findViewById(R.id.tvBillAmount1);
        tvBillAmount2 = (TextView)findViewById(R.id.tvBillAmount2);
        tvBillAmount3 = (TextView)findViewById(R.id.tvBillAmount3);
        tvBillAmount4 = (TextView)findViewById(R.id.tvBillAmount4);
        tvBillAmount5 = (TextView)findViewById(R.id.tvBillAmount5);
        tvBillAmount6 = (TextView)findViewById(R.id.tvBillAmount6);

        btnBillDetails1 = (Button) findViewById(R.id.btnBillDetails1);
        btnBillDetails2 = (Button) findViewById(R.id.btnBillDetails2);
        btnBillDetails3 = (Button) findViewById(R.id.btnBillDetails3);
        btnBillDetails4 = (Button) findViewById(R.id.btnBillDetails4);
        btnBillDetails5 = (Button) findViewById(R.id.btnBillDetails5);
        btnBillDetails6 = (Button) findViewById(R.id.btnBillDetails6);

        if (dates.isEmpty() && prices.isEmpty()) {
            Cursor paymentDetails = cusController.getPaymentDetails(userName);

            paymentDetails.moveToFirst();

            while(!paymentDetails.isAfterLast()) {
                dates.add(paymentDetails.getString(0));
                prices.add(paymentDetails.getString(1));

                paymentDetails.moveToNext();
            }
            paymentDetails.close();
        }
        else if (!dates.isEmpty() && !prices.isEmpty()) {
            dates.clear();
            prices.clear();

            Cursor paymentDetails = cusController.getPaymentDetails(userName);

            paymentDetails.moveToFirst();

            while(!paymentDetails.isAfterLast()) {
                dates.add(paymentDetails.getString(0));
                prices.add(paymentDetails.getString(1));

                paymentDetails.moveToNext();
            }
            paymentDetails.close();
        }
        System.out.println(dates);
        System.out.println(prices);

        date1 = dates.get(5);
        date2 = dates.get(4);
        date3 = dates.get(3);
        date4 = dates.get(2);
        date5 = dates.get(1);
        date6 = dates.get(0);

        tvShowDate.setText(date1);
        tvShowDate2.setText(date2);
        tvShowDate3.setText(date3);
        tvShowDate4.setText(date4);
        tvShowDate5.setText(date5);
        tvShowDate6.setText(date6);

        price1 = prices.get(5);
        price2 = prices.get(4);
        price3 = prices.get(3);
        price4 = prices.get(2);
        price5 = prices.get(1);
        price6 = prices.get(0);

        tvBillAmount1.setText(price1);
        tvBillAmount2.setText(price2);
        tvBillAmount3.setText(price3);
        tvBillAmount4.setText(price4);
        tvBillAmount5.setText(price5);
        tvBillAmount5.setText(price6);

        returnHomeButton = (Button) findViewById(R.id.returnHomeButton);
        returnHomeButton.setOnClickListener(returnHome);

        btnBillDetails1.setOnClickListener(billDetail1);
        btnBillDetails2.setOnClickListener(billDetail2);
        btnBillDetails3.setOnClickListener(billDetail3);
        btnBillDetails4.setOnClickListener(billDetail4);
        btnBillDetails5.setOnClickListener(billDetail5);
        btnBillDetails6.setOnClickListener(billDetail6);
    }

    private View.OnClickListener returnHome = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent returnHomeActivity = new Intent (viewPaymentDetails.this, MainActivity.class);
            returnHomeActivity.putExtra("userName", userName);
            returnHomeActivity.putExtra("password", password);
            startActivity(returnHomeActivity);;
        }
    };

    private View.OnClickListener billDetail1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent billDetailsActivity = new Intent (viewPaymentDetails.this, billDetails.class);
            billDetailsActivity.putExtra("userName", userName);
            billDetailsActivity.putExtra("password", password);

            startDate = date1;
            totalCost = price1;

            billDetailsActivity.putExtra("startDate", startDate);
            billDetailsActivity.putExtra("totalCost", totalCost);
            startActivity(billDetailsActivity);;
        }
    };

    private View.OnClickListener billDetail2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent billDetailsActivity = new Intent (viewPaymentDetails.this, billDetails.class);
            billDetailsActivity.putExtra("userName", userName);
            billDetailsActivity.putExtra("password", password);

            startDate = date2;
            totalCost = price2;

            billDetailsActivity.putExtra("startDate", startDate);
            billDetailsActivity.putExtra("totalCost", totalCost);
            startActivity(billDetailsActivity);;
        }
    };

    private View.OnClickListener billDetail3 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent billDetailsActivity = new Intent (viewPaymentDetails.this, billDetails.class);
            billDetailsActivity.putExtra("userName", userName);
            billDetailsActivity.putExtra("password", password);

            startDate = date3;
            totalCost = price3;

            billDetailsActivity.putExtra("startDate", startDate);
            billDetailsActivity.putExtra("totalCost", totalCost);
            startActivity(billDetailsActivity);;
        }
    };

    private View.OnClickListener billDetail4 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent billDetailsActivity = new Intent (viewPaymentDetails.this, billDetails.class);
            billDetailsActivity.putExtra("userName", userName);
            billDetailsActivity.putExtra("password", password);

            startDate = date4;
            totalCost = price4;

            billDetailsActivity.putExtra("startDate", startDate);
            billDetailsActivity.putExtra("totalCost", totalCost);
            startActivity(billDetailsActivity);;
        }
    };

    private View.OnClickListener billDetail5 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent billDetailsActivity = new Intent (viewPaymentDetails.this, billDetails.class);
            billDetailsActivity.putExtra("userName", userName);
            billDetailsActivity.putExtra("password", password);

            startDate = date5;
            totalCost = price5;

            billDetailsActivity.putExtra("startDate", startDate);
            billDetailsActivity.putExtra("totalCost", totalCost);
            startActivity(billDetailsActivity);;
        }
    };

    private View.OnClickListener billDetail6 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent billDetailsActivity = new Intent (viewPaymentDetails.this, billDetails.class);
            billDetailsActivity.putExtra("userName", userName);
            billDetailsActivity.putExtra("password", password);

            startDate = date6;
            totalCost = price6;

            billDetailsActivity.putExtra("startDate", startDate);
            billDetailsActivity.putExtra("totalCost", totalCost);
            startActivity(billDetailsActivity);;
        }
    };
}