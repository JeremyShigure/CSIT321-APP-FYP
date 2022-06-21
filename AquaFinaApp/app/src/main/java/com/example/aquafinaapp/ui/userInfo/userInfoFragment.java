package com.example.aquafinaapp.ui.userInfo;

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
import com.example.aquafinaapp.databinding.FragmentUserInfoBinding;
import com.example.aquafinaapp.ui.loginPage.loginPage;
import com.example.aquafinaapp.ui.paymentSuccessful.paymentSuccessful;

public class userInfoFragment extends Fragment implements View.OnClickListener{

    Button viewUserInfoButton, updateUserInfoButton, aboutUsInfoButton, logoutButton;

    private FragmentUserInfoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        userInfoViewModel InfoViewModel = new ViewModelProvider(this).get(userInfoViewModel.class);

        binding = FragmentUserInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewUserInfoButton = (Button) root.findViewById(R.id.viewUserInfoButton);
        updateUserInfoButton = (Button) root.findViewById(R.id.updateUserInfoButton);
        aboutUsInfoButton = (Button) root.findViewById(R.id.aboutUsInfoButton);
        logoutButton = (Button) root.findViewById(R.id.logoutButton);

        viewUserInfoButton.setOnClickListener(viewUserInfo);
        updateUserInfoButton.setOnClickListener(updateUserInfo);
        aboutUsInfoButton.setOnClickListener(aboutUs);

        logoutButton.setOnClickListener(this);

//        final TextView textView = binding.textNotifications;
//        InfoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {

        new AlertDialog.Builder(getActivity())
                .setTitle("Logout?")
                .setMessage("Are you sure you want to logout?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
//                        setResult(RESULT_OK, new Intent().putExtra("EXIT", true));
                        Intent logoutConfirm = new Intent(getActivity(), loginPage.class);
                        startActivity(logoutConfirm);
//                        finish();
                        Toast.makeText(getActivity(), "Log out successfully\n see you again!", Toast.LENGTH_SHORT).show();
                    }

                }).create().show();
    }

    private View.OnClickListener viewUserInfo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent viewUserInfoActivity = new Intent(getActivity(), viewUserInfo.class);
            startActivity(viewUserInfoActivity);
        }
    };

    private View.OnClickListener updateUserInfo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent updateUserInfoActivity = new Intent(getActivity(), updateUserInfo.class);
            startActivity(updateUserInfoActivity);
        }
    };

    private View.OnClickListener aboutUs = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent aboutUsActivity = new Intent(getActivity(), aboutUs.class);
            startActivity(aboutUsActivity);
        }
    };



}