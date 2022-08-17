package com.example.aquafinaapp.ui.faq;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aquafinaapp.Controller.customerController;
import com.example.aquafinaapp.R;
import com.example.aquafinaapp.databinding.FragmentFaqBinding;

import java.util.ArrayList;
import java.util.List;

public class FAQFragment extends Fragment {

    private FragmentFaqBinding binding;

    RecyclerView recyclerViewFAQ;
    List<faqWords> faqQuestionsList;

    customerController cusController = new customerController();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FAQViewModel faqViewModel = new ViewModelProvider(this).get(FAQViewModel.class);

        binding = FragmentFaqBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerViewFAQ = root.findViewById(R.id.recyclerViewFAQ);

        initData();
        setRecyclerView();

        return root;
    }

    private void setRecyclerView() {
        faqAdapter faqAdapter = new faqAdapter(faqQuestionsList);
        recyclerViewFAQ.setAdapter(faqAdapter);
        recyclerViewFAQ.setHasFixedSize(true);
    }

    private void initData() {
        faqQuestionsList = new ArrayList<>();

        Cursor faq = cusController.getFAQInfo();

        faq.moveToFirst();

        while(!faq.isAfterLast()) {
            faqQuestionsList.add(new faqWords(faq.getString(1), faq.getString(2)));
            faq.moveToNext();
        }
        faq.close();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
