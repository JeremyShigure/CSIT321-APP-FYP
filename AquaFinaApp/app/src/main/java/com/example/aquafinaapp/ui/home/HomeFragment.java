package com.example.aquafinaapp.ui.home;

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
import com.example.aquafinaapp.databinding.FragmentHomeBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.firebase.firestore.util.Util;

import java.util.ArrayList;
import java.util.Random;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private String userName;
    private String password;
    private String hintWords;

    TextView textUserName, textUserHint;

    TextView tvHouseType, tvPredictedValue, tvActualValue;

    customerController cusController = new customerController();


    // Bar Chart things
    // -------------------------------------------------------------------------------------------------------------------------------------------------
    private static final String SET_LABEL = "Water usage in cuM (Cubic Meter)";
    private static String[] MONTHS = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};

    private static ArrayList<String> days = new ArrayList<>();
    private static ArrayList<String> usages = new ArrayList<>();
    private static ArrayList<String> daysToString = new ArrayList<>();

    private static ArrayList<String> cHouseType = new ArrayList<>();
    private static String getLastCHouseType = "";
    private static String getLastMonth = "";
    private static String getLastUsage = "";

    private static String predictUsage = "";

    private BarChart chart;

    // -------------------------------------------------------------------------------------------------------------------------------------------------


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Intent intent = getActivity().getIntent();

        Bundle bundle = getActivity().getIntent().getExtras();
        userName = bundle.getString("userName");
        password = bundle.getString("password");

        // First time run this code is confirm empty, so will run here
        if (days.isEmpty() && usages.isEmpty()) {
            Cursor water = cusController.getMeterInfo(userName);

            water.moveToFirst();

            while(!water.isAfterLast()) {
                days.add(water.getString(0));
                usages.add(water.getString(1));
                cHouseType.add(water.getString(2));
                water.moveToNext();
            }
            water.close();

            for (int i = 0; i < days.size(); i++) {
                String [] a = days.get(i).split("/");

                daysToString.add(returnMonth(a[1]));

            }
        }

        // Starting from second time, it will auto delete the arraylists and run this section
        else if (!days.isEmpty() && !usages.isEmpty()) {
            days.clear();
            usages.clear();
            cHouseType.clear();
            daysToString.clear();

            Cursor water = cusController.getMeterInfo(userName);

            water.moveToFirst();

            while(!water.isAfterLast()) {
                days.add(water.getString(0));
                usages.add(water.getString(1));
                cHouseType.add(water.getString(2));
                water.moveToNext();
            }

            for (int i = 0; i < days.size(); i++) {
                String [] a = days.get(i).split("/");

                daysToString.add(returnMonth(a[1]));
            }
        }

        // Cursor of getting the predicted average value of water
        getLastCHouseType = cHouseType.get(5);
        getLastMonth = daysToString.get(5);
        getLastUsage = usages.get(5);
        Cursor predictWater = cusController.viewAveWaterUsage(getLastCHouseType, getLastMonth);

        while(predictWater.moveToNext()) {
            predictUsage = predictWater.getString(2);
        }
        predictWater.close();

        // Bar Chart things
        chart = root.findViewById(R.id.fragmentBarChart);

        BarData data = createChartData();
        configureChartAppearance();
        prepareChartData(data);

        textUserName = (TextView) root.findViewById(R.id.textUserName);
        textUserHint = (TextView) root.findViewById(R.id.textUserHint);

        tvHouseType = (TextView) root.findViewById(R.id.tvHouseType);

        tvPredictedValue = (TextView) root.findViewById(R.id.tvPredictedValue);
        tvActualValue = (TextView) root.findViewById(R.id.tvActualValue);

        textUserName.setText("Welcome \n" + userName);
        textUserHint.setText(hintWords);

        tvHouseType.setText("Your house type: " + getLastCHouseType + " rooms");

        tvPredictedValue.setText(predictUsage + " cuM");
        tvActualValue.setText(getLastUsage + " cuM");

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void configureChartAppearance() {

        // I set everything to false to that the customer will not able to zoom in the things in the bar chart

        chart.getDescription().setEnabled(false);
        chart.setDrawValueAboveBar(false);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);
        chart.setScaleXEnabled(false);
        chart.setScaleYEnabled(false);
        chart.setDragEnabled(false);
        chart.setTouchEnabled(false);

        XAxis xAxis = chart.getXAxis();

        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return daysToString.get((int) value);
            }
        });

        YAxis axisLeft = chart.getAxisLeft();
        axisLeft.setGranularity(10f);
        axisLeft.setAxisMinimum(0);


        YAxis axisRight = chart.getAxisRight();
        axisRight.setAxisMinimum(0);
    }

    private BarData createChartData() {
        ArrayList<BarEntry> values = new ArrayList<>();
        Random rand = new Random();

        ArrayList <Float> yValues = new ArrayList<>();

        for (int i = 0; i < usages.size(); i++) {
            float x = i;
            float y = Float.parseFloat(usages.get(i));

            values.add(new BarEntry(x, y));

            yValues.add(y);
        }

        // Set to textview based on last month vs current month and average household usage vs current month usage
        if (yValues.get(4) > yValues.get(5) && yValues.get(5) < Integer.valueOf(predictUsage)) {
            hintWords = "Good job on saving the water! \nYou are below average household usage!";
        }
        else if (yValues.get(4) < yValues.get(5) && yValues.get(5) > Integer.valueOf(predictUsage)) {
            hintWords = "We must save mother Earth \nby saving more water!";
        }
        else if (yValues.get(4) > yValues.get(5) && yValues.get(5) > Integer.valueOf(predictUsage)) {
            hintWords = "Good job on saving the water, \nbut you are still higher than average households usage";
        }
        else if (yValues.get(4) < yValues.get(5) && yValues.get(5) < Integer.valueOf(predictUsage)) {
            hintWords = "Your water usage is below average but \ntry to conserve some water!";
        }
        else {
            hintWords = "You can do it!! Keep it up on saving water usages!";
        }

        BarDataSet set1 = new BarDataSet(values, SET_LABEL);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);

        return data;
    }

    private void prepareChartData(BarData data) {
        data.setValueTextSize(12f);
        chart.setData(data);
        chart.invalidate();
    }


    // Convert the month based on the month retrieve from database to month initials
    private String returnMonth(String month) {
        String convert = "";

        switch(month) {
            case "1":
                convert = MONTHS[0];
                break;
            case "2":
                convert = MONTHS[1];
                break;
            case "3":
                convert = MONTHS[2];
                break;
            case "4":
                convert = MONTHS[3];
                break;
            case "5":
                convert = MONTHS[4];
                break;
            case "6":
                convert = MONTHS[5];
                break;
            case "7":
                convert = MONTHS[6];
                break;
            case "8":
                convert = MONTHS[7];
                break;
            case "9":
                convert = MONTHS[8];
                break;
            case "10":
                convert = MONTHS[9];
                break;
            case "11":
                convert = MONTHS[10];
                break;
            case "12":
                convert = MONTHS[11];
                break;
        }
        return convert;
    }
}
