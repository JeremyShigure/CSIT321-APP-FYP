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

        faqQuestionsList.add(new faqWords("This is question 1", "Try see this faq question 1 for more info yey asdasdsadsadasdsadsadsadsadas  asdas asd asdsa dasdas dasd adas dasdasdsadsad das as das da das das das dsadasdasdasdasdasdsdasdsdaadsend of words"));
        faqQuestionsList.add(new faqWords("This is question 2", "Try see this faq question 2 for more info yey "));
        faqQuestionsList.add(new faqWords("This is question 3", "Try see this faq question 3 for more info yey "));
        faqQuestionsList.add(new faqWords("This is question 4", "Try see this faq question 4 for more info yey "));
        faqQuestionsList.add(new faqWords("This is question 5", "Try see this faq question 5 for more info yey "));
        faqQuestionsList.add(new faqWords("This is question 6", "Try see this faq question 6 for more info yey "));
//        faqQuestionsList.add(new faqWords("", ""));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
