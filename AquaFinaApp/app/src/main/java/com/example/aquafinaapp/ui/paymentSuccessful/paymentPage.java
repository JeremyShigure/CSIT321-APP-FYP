package com.example.aquafinaapp.ui.paymentSuccessful;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aquafinaapp.R;
import com.example.aquafinaapp.ui.payment.newestMonthBillDetails;


public class paymentPage extends AppCompatActivity {

    private String userName;
    private String password;

    private String invoiceID;
    private String totalCost;

    EditText etCreditCard, etExpireMonth, etExpireYear, etCVC, etName;

    Button btnConfirm, btnCancelPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_page);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        password = intent.getStringExtra("password");
        invoiceID = intent.getStringExtra("invoiceID");
        totalCost = intent.getStringExtra("totalCost");

        etCreditCard = (EditText) findViewById(R.id.etCreditCard);
        etExpireMonth = (EditText) findViewById(R.id.etExpireMonth);
        etExpireYear = (EditText) findViewById(R.id.etExpireYear);
        etCVC = (EditText) findViewById(R.id.etCVC);
        etName = (EditText) findViewById(R.id.etName);

        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnCancelPayment = (Button) findViewById(R.id.btnCancelPayment);

        btnConfirm.setOnClickListener(confirmPayment);
        btnCancelPayment.setOnClickListener(cancelPayment);
    }

    private View.OnClickListener confirmPayment = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (etCreditCard.getText().toString().isEmpty() && etExpireMonth.getText().toString().isEmpty() && etExpireYear.getText().toString().isEmpty() && etCVC.getText().toString().isEmpty() && etName.getText().toString().isEmpty()) {
                Toast.makeText(paymentPage.this, "One or more fields is empty!", Toast.LENGTH_SHORT).show();
            }
            else if (etCreditCard.getText().toString().length() != 16) {
                Toast.makeText(paymentPage.this, "Please enter correct credit card details!", Toast.LENGTH_SHORT).show();
            }
            else if (etExpireMonth.getText().toString().length() != 2 || etExpireYear.getText().toString().length() != 2) {
                Toast.makeText(paymentPage.this, "Please enter expiry date format!", Toast.LENGTH_SHORT).show();
            }
            else if (etCVC.getText().toString().length() != 3) {
                Toast.makeText(paymentPage.this, "Please enter correct CVC format!", Toast.LENGTH_SHORT).show();
            }
            else if (etName.getText().toString().isEmpty()) {
                Toast.makeText(paymentPage.this, "Card name cannot be empty!", Toast.LENGTH_SHORT).show();
            }
            else if (Integer.parseInt(etExpireMonth.getText().toString()) > 12) {
                Toast.makeText(paymentPage.this, "Invalid month!", Toast.LENGTH_SHORT).show();
            }
            else if (Integer.parseInt(etExpireYear.getText().toString()) < 21) {
                Toast.makeText(paymentPage.this, "Invalid year!", Toast.LENGTH_SHORT).show();
            }
            else {

                new AlertDialog.Builder(paymentPage.this)
                        .setTitle("Make Payment?")
                        .setMessage("Please make sure all the details are correct!")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                setResult(RESULT_OK, new Intent().putExtra("EXIT", true));
                                Intent confirmPaymentActivity = new Intent (paymentPage.this, paymentSuccessfulPage.class);
                                confirmPaymentActivity.putExtra("userName", userName);
                                confirmPaymentActivity.putExtra("password", password);
                                startActivity(confirmPaymentActivity);

                                finish();
                                Toast.makeText(paymentPage.this, "Payment Successful!\nHave a nice day!", Toast.LENGTH_SHORT).show();
                            }
                        }).create().show();
            }
        }
    };

    private View.OnClickListener cancelPayment = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            new AlertDialog.Builder(paymentPage.this)
                    .setTitle("Cancel Payment?")
                    .setMessage("You will need to re-enter all the details again")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            setResult(RESULT_OK, new Intent().putExtra("EXIT", true));
                            Intent cancelPaymentActivity = new Intent (paymentPage.this, newestMonthBillDetails.class);
                            cancelPaymentActivity.putExtra("userName", userName);
                            cancelPaymentActivity.putExtra("password", password);

                            cancelPaymentActivity.putExtra("invoiceID", invoiceID);
                            cancelPaymentActivity.putExtra("totalCost", totalCost);

                            startActivity(cancelPaymentActivity);

                            finish();
                            Toast.makeText(paymentPage.this, "Payment Cancelled!", Toast.LENGTH_SHORT).show();
                        }

                    }).create().show();
        }
    };
}