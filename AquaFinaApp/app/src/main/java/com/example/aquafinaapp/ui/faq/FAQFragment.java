package com.example.aquafinaapp.ui.faq;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aquafinaapp.R;
import com.example.aquafinaapp.databinding.FragmentFaqBinding;

import java.util.ArrayList;
import java.util.List;

public class FAQFragment extends Fragment {

    private FragmentFaqBinding binding;

    RecyclerView recyclerView;
    List<faqWords> faqQuestionsList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FAQViewModel faqViewModel = new ViewModelProvider(this).get(FAQViewModel.class);

        binding = FragmentFaqBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        recyclerView = root.findViewById(R.id.recyclerViewFAQ);

        initData();
        setRecyclerView();

//        final TextView textView = binding.textFaq;
//        faqViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private void setRecyclerView() {
        faqAdapter faqAdapter = new faqAdapter(faqQuestionsList);
        recyclerView.setAdapter(faqAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {
        faqQuestionsList = new ArrayList<>();

        faqQuestionsList.add(new faqWords("What if I am moving to another address?", "Please call our hotline to inform our staffs of your address change and other assistance"));
        faqQuestionsList.add(new faqWords("My water meter is not recording accurately", "Please book an appointment with us to fix your water meter"));
        faqQuestionsList.add(new faqWords("What if I changed my mobile number?", "Please head to your profile under the 'More' options below to update your particulars"));
        faqQuestionsList.add(new faqWords("What if I forget my password?", "Please click on the 'Forgot Password' button on the login page to reset your password"));
        faqQuestionsList.add(new faqWords("I would like to enquire about other information", "You can contact us at 68302991. Alternatively, you can also email us at admin@aquafina.com"));
        faqQuestionsList.add(new faqWords("My username has been compromised, what can I do?", "You can head to your profile and update your account with a new username and password"));
//        faqQuestionsList.add(new faqWords("", ""));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
