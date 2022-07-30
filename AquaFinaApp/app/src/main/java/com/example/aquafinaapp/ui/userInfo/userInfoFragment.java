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
import com.example.aquafinaapp.ui.loginPage.custLogin;

public class userInfoFragment extends Fragment implements View.OnClickListener{

    TextView viewUserInfoButton, aboutUsInfoButton, logoutButton;

    private FragmentUserInfoBinding binding;

    private String userName;
    private String password;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        userInfoViewModel InfoViewModel = new ViewModelProvider(this).get(userInfoViewModel.class);

        binding = FragmentUserInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Intent intent = getActivity().getIntent();
        userName = intent.getStringExtra("userName");
        password = intent.getStringExtra("password");

        Bundle bundle = getArguments();
        if (bundle != null) {
            userName = bundle.getString("userName");
            password = bundle.getString("password");
        }

//        Bundle bundle = getActivity().getIntent().getExtras();

//        Bundle bundle = getArguments();
//        userName = bundle.getString("userName");
//        password = bundle.getString("password");

        viewUserInfoButton = (TextView) root.findViewById(R.id.viewUserInfoButton);
        aboutUsInfoButton = (TextView) root.findViewById(R.id.aboutUsInfoButton);
        logoutButton = (TextView) root.findViewById(R.id.logoutButton);

        viewUserInfoButton.setOnClickListener(viewUserInfo);
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
                        Intent logoutConfirm = new Intent(getActivity(), custLogin.class);
                        startActivity(logoutConfirm);
//                        finish();
                        Toast.makeText(getActivity(), "Log out successfully\nsee you again!", Toast.LENGTH_SHORT).show();
                    }

                }).create().show();
    }

    // Go to viewUserInfo page
    private View.OnClickListener viewUserInfo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent viewUserInfoActivity = new Intent(getActivity(), viewUserInfo.class);
            viewUserInfoActivity.putExtra("userName", userName);
            viewUserInfoActivity.putExtra("password", password);
            startActivity(viewUserInfoActivity);
        }
    };


    // Go to about Us page
    private View.OnClickListener aboutUs = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent aboutUsActivity = new Intent(getActivity(), aboutUs.class);
            aboutUsActivity.putExtra("userName", userName);
            aboutUsActivity.putExtra("password", password);
            startActivity(aboutUsActivity);
        }
    };



}