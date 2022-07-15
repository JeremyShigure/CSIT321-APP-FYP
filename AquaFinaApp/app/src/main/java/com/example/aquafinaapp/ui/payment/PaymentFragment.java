package com.example.aquafinaapp.ui.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.aquafinaapp.R;
import com.example.aquafinaapp.databinding.FragmentPaymentBinding;
import com.example.aquafinaapp.ui.paymentSuccessful.viewPaymentDetails;
import com.example.aquafinaapp.ui.userInfo.viewUserInfo;

public class PaymentFragment extends Fragment {

    Button btnViewInvoice, btnBillDetails;

    private FragmentPaymentBinding binding;

    private String userName;
    private String password;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        PaymentViewModel dashboardViewModel = new ViewModelProvider(this).get(PaymentViewModel.class);

        binding = FragmentPaymentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Intent intent = getActivity().getIntent();

        Bundle bundle = getActivity().getIntent().getExtras();
        userName = bundle.getString("userName");
        password = bundle.getString("password");

        btnViewInvoice = root.findViewById(R.id.btnViewInvoice);
        btnViewInvoice.setOnClickListener(viewInvoice);

        btnBillDetails = root.findViewById(R.id.btnBillDetails);
        btnBillDetails.setOnClickListener(billDetails);

//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private View.OnClickListener viewInvoice = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent viewInvoiceActivity = new Intent(getActivity(), viewPaymentDetails.class);
            viewInvoiceActivity.putExtra("userName", userName);
            viewInvoiceActivity.putExtra("password", password);
            startActivity(viewInvoiceActivity);
        }
    };


    private View.OnClickListener billDetails = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent billDetailsActivity = new Intent(getActivity(), billDetails.class);
            billDetailsActivity.putExtra("userName", userName);
            billDetailsActivity.putExtra("password", password);
            startActivity(billDetailsActivity);
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