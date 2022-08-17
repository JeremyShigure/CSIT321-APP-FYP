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

public class billDetails extends AppCompatActivity {

    Button returnHomeButton;
    TextView tvAccountNo, tvInvoiceID, tvStartDate, tvEndDate, tvNettCost, tvConsTax, tvBorneFee, tvSubtotal, tvGST, tvTotalAmt, tvShowDateIssuedWords, tvShowDueDateWords;

    private String userName;
    private String password;

    private String startDate;
    private String totalCost;

    customerController cusController = new customerController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        password = intent.getStringExtra("password");

        startDate = intent.getStringExtra("startDate");
        totalCost = intent.getStringExtra("totalCost");

        // Declaring variables
        tvAccountNo = (TextView)findViewById(R.id.tvAccountNo);
        tvInvoiceID = (TextView)findViewById(R.id.tvInvoiceID);
        tvStartDate = (TextView)findViewById(R.id.tvStartDate);
        tvEndDate = (TextView)findViewById(R.id.tvEndDate);
        tvNettCost = (TextView)findViewById(R.id.tvNettCost);
        tvConsTax = (TextView)findViewById(R.id.tvConsTax);
        tvBorneFee = (TextView)findViewById(R.id.tvBorneFee);
        tvSubtotal = (TextView)findViewById(R.id.tvSubtotal);
        tvGST = (TextView)findViewById(R.id.tvGST);
        tvTotalAmt = (TextView)findViewById(R.id.tvTotalAmt);
        tvShowDateIssuedWords = (TextView)findViewById(R.id.tvShowDateIssuedWords);
        tvShowDueDateWords = (TextView)findViewById(R.id.tvShowDueDateWords);

        Cursor billInvoiceDetails = cusController.getInvoiceInfo(startDate, totalCost, userName);

        if (billInvoiceDetails != null && billInvoiceDetails.getCount() > 0) {
            if (billInvoiceDetails.moveToFirst()) {
                tvAccountNo.setText(billInvoiceDetails.getString(0));
                tvInvoiceID.setText(billInvoiceDetails.getString(1));
                tvStartDate.setText(billInvoiceDetails.getString(2));
                tvEndDate.setText(billInvoiceDetails.getString(3));
                tvNettCost.setText(billInvoiceDetails.getString(4));
                tvConsTax.setText(billInvoiceDetails.getString(5));
                tvBorneFee.setText(billInvoiceDetails.getString(6));
                tvSubtotal.setText(billInvoiceDetails.getString(7));
                tvGST.setText(billInvoiceDetails.getString(8));
                tvTotalAmt.setText(billInvoiceDetails.getString(9));
                tvShowDateIssuedWords.setText(billInvoiceDetails.getString(10));
                tvShowDueDateWords.setText(billInvoiceDetails.getString(11));
            }
        }
        billInvoiceDetails.close();

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