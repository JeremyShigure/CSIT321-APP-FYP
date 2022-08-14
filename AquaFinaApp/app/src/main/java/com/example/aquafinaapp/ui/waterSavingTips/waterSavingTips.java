package com.example.aquafinaapp.ui.waterSavingTips;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.example.aquafinaapp.Controller.customerController;
import com.example.aquafinaapp.R;
import com.example.aquafinaapp.databinding.FragmentFaqBinding;
import com.example.aquafinaapp.ui.faq.faqAdapter;
import com.example.aquafinaapp.ui.faq.faqWords;

import java.util.ArrayList;
import java.util.List;

public class waterSavingTips extends AppCompatActivity {

    private FragmentFaqBinding binding;

    RecyclerView recyclerViewTips;
    List<waterSavingTipsWords> tipsQuestionsList;

    customerController cusController = new customerController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_saving_tips);

//        binding = FragmentFaqBinding.inflate(inflater, container, false);

        recyclerViewTips = (RecyclerView) findViewById(R.id.recyclerViewTips);

        initData();
        setRecyclerView();

    }

    private void setRecyclerView() {
        waterSavingTipsAdapter wstAdapter = new waterSavingTipsAdapter(tipsQuestionsList);
        recyclerViewTips.setAdapter(wstAdapter);
        recyclerViewTips.setHasFixedSize(true);
    }

    private void initData() {
        tipsQuestionsList = new ArrayList<>();

        Cursor tips = cusController.getWaterSavingTips();

        tips.moveToFirst();

        while (!tips.isAfterLast()) {
            tipsQuestionsList.add(new waterSavingTipsWords(tips.getString(1), tips.getString(2)));
            tips.moveToNext();
        }
        tips.close();
    }
}