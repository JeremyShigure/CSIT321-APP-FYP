package com.example.aquafinaapp.ui.payment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.aquafinaapp.R;
import com.example.aquafinaapp.databinding.FragmentPaymentBinding;
import com.example.aquafinaapp.ui.paymentSuccessful.paymentSuccessful;

public class PaymentFragment extends Fragment implements View.OnClickListener {

    Button paymentButton;

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

        paymentButton = (Button) root.findViewById(R.id.paymentButton);
        paymentButton.setOnClickListener(this);

//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {


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

        Intent paymentConfirm = new Intent(getActivity(), paymentSuccessful.class);
        paymentConfirm.putExtra("userName", userName);
        paymentConfirm.putExtra("password", password);
        startActivity(paymentConfirm);
    }

}