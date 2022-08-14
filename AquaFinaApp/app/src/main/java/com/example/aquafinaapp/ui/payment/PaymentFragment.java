package com.example.aquafinaapp.ui.payment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.aquafinaapp.Controller.customerController;
import com.example.aquafinaapp.R;
import com.example.aquafinaapp.databinding.FragmentPaymentBinding;

public class PaymentFragment extends Fragment {

    Button btnViewInvoice, btnBillDetails;
    TextView tvInvoiceID, tvBillAmount;

    private FragmentPaymentBinding binding;

    private String userName;
    private String password;

    private String invoiceID;
    private String totalCost;


    customerController cusController = new customerController();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        PaymentViewModel dashboardViewModel = new ViewModelProvider(this).get(PaymentViewModel.class);

        binding = FragmentPaymentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Intent intent = getActivity().getIntent();

        Bundle bundle = getActivity().getIntent().getExtras();
        userName = bundle.getString("userName");
        password = bundle.getString("password");

        tvInvoiceID = root.findViewById(R.id.tvInvoiceID);
        tvBillAmount = root.findViewById(R.id.tvBillAmount);

        Cursor newestBillDetails = cusController.getNewestPaymentDetails(userName);

        StringBuffer buffer = new StringBuffer();

        if(newestBillDetails != null && newestBillDetails.getCount() > 0)
        {
            if (newestBillDetails.moveToFirst()) {
                invoiceID = newestBillDetails.getString(0);
                totalCost = newestBillDetails.getString(1);

                tvInvoiceID.setText(invoiceID);
                tvBillAmount.setText(totalCost);
            }
        }
        newestBillDetails.close();

        btnViewInvoice = root.findViewById(R.id.btnViewInvoice);
        btnViewInvoice.setOnClickListener(viewPaymentDetails);

        btnBillDetails = root.findViewById(R.id.btnBillDetails);
        btnBillDetails.setOnClickListener(viewNewestInvoice);

//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private View.OnClickListener viewNewestInvoice = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent viewNewestInvoiceActivity = new Intent(getActivity(), newestMonthBillDetails.class);
            viewNewestInvoiceActivity.putExtra("userName", userName);
            viewNewestInvoiceActivity.putExtra("password", password);

            viewNewestInvoiceActivity.putExtra("invoiceID", invoiceID);
            viewNewestInvoiceActivity.putExtra("totalCost", totalCost);

            System.out.println("invoiceID: " + invoiceID);
            System.out.println("totalcost: " + totalCost);

            System.out.println("EEEEEEEEEENNNNNNNNNNNNTTTTTTTTTTTEEEEEEEEERRRRRRRRRRRRIIIIIIIIIIINNNNNNNNNNNNGGGGGGGGG now");
            startActivity(viewNewestInvoiceActivity);
        }
    };


    private View.OnClickListener viewPaymentDetails = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent viewPaymentDetailsActivity = new Intent(getActivity(), viewPaymentDetails.class);
            viewPaymentDetailsActivity.putExtra("userName", userName);
            viewPaymentDetailsActivity.putExtra("password", password);
            startActivity(viewPaymentDetailsActivity);
        }
    };

//
//    @Override
//    public void onClick(View view) {


        // THIS WHOLE CHUNK IS THE DIALOG FOR PRESSING YES TO GO TO NEXT PAGE, AND NO TO REMAIN AT THIS PAGE
        //-------------------------------------------------------------------------------------------------------------------------
//
//        new AlertDialog.Builder(getActivity())
//                .setTitle("Please Check the payment amount?")
//                .setMessage("Payment price here")
//                .setNegativeButton(android.R.string.no, null)
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface arg0, int arg1) {
////                        setResult(RESULT_OK, new Intent().putExtra("EXIT", true));
//                        Intent paymentConfirm = new Intent(getActivity(), paymentSuccessful.class);
//                        paymentConfirm.putExtra("userName", userName);
//                        paymentConfirm.putExtra("password", password);
//                        startActivity(paymentConfirm);
////                        finish();
//                        Toast.makeText(getActivity(), "Payment successfully", Toast.LENGTH_SHORT).show();
//                    }
//
//                }).create().show();
        //-------------------------------------------------------------------------------------------------------------------------


        // THE REAL CODE IS HERE ~~

//        Intent paymentConfirm = new Intent(getActivity(), viewPaymentDetails.class);
//        paymentConfirm.putExtra("userName", userName);
//        paymentConfirm.putExtra("password", password);
//        startActivity(paymentConfirm);
//    }

}