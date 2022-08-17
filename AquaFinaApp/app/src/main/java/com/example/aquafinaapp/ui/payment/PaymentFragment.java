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
}