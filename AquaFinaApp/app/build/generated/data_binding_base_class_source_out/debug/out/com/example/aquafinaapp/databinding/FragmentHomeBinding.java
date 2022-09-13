// Generated by view binder compiler. Do not edit!
package com.example.aquafinaapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.aquafinaapp.R;
import com.github.mikephil.charting.charts.BarChart;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout clActual;

  @NonNull
  public final ConstraintLayout clExtra;

  @NonNull
  public final ConstraintLayout clPredicted;

  @NonNull
  public final BarChart fragmentBarChart;

  @NonNull
  public final TextView textUserHint;

  @NonNull
  public final TextView textUserName;

  @NonNull
  public final TextView tvActualValue;

  @NonNull
  public final TextView tvActualWords;

  @NonNull
  public final TextView tvHouseType;

  @NonNull
  public final TextView tvInformation;

  @NonNull
  public final TextView tvPredictedValue;

  @NonNull
  public final TextView tvPredictedWords;

  private FragmentHomeBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout clActual, @NonNull ConstraintLayout clExtra,
      @NonNull ConstraintLayout clPredicted, @NonNull BarChart fragmentBarChart,
      @NonNull TextView textUserHint, @NonNull TextView textUserName,
      @NonNull TextView tvActualValue, @NonNull TextView tvActualWords,
      @NonNull TextView tvHouseType, @NonNull TextView tvInformation,
      @NonNull TextView tvPredictedValue, @NonNull TextView tvPredictedWords) {
    this.rootView = rootView;
    this.clActual = clActual;
    this.clExtra = clExtra;
    this.clPredicted = clPredicted;
    this.fragmentBarChart = fragmentBarChart;
    this.textUserHint = textUserHint;
    this.textUserName = textUserName;
    this.tvActualValue = tvActualValue;
    this.tvActualWords = tvActualWords;
    this.tvHouseType = tvHouseType;
    this.tvInformation = tvInformation;
    this.tvPredictedValue = tvPredictedValue;
    this.tvPredictedWords = tvPredictedWords;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.clActual;
      ConstraintLayout clActual = ViewBindings.findChildViewById(rootView, id);
      if (clActual == null) {
        break missingId;
      }

      id = R.id.clExtra;
      ConstraintLayout clExtra = ViewBindings.findChildViewById(rootView, id);
      if (clExtra == null) {
        break missingId;
      }

      id = R.id.clPredicted;
      ConstraintLayout clPredicted = ViewBindings.findChildViewById(rootView, id);
      if (clPredicted == null) {
        break missingId;
      }

      id = R.id.fragmentBarChart;
      BarChart fragmentBarChart = ViewBindings.findChildViewById(rootView, id);
      if (fragmentBarChart == null) {
        break missingId;
      }

      id = R.id.textUserHint;
      TextView textUserHint = ViewBindings.findChildViewById(rootView, id);
      if (textUserHint == null) {
        break missingId;
      }

      id = R.id.textUserName;
      TextView textUserName = ViewBindings.findChildViewById(rootView, id);
      if (textUserName == null) {
        break missingId;
      }

      id = R.id.tvActualValue;
      TextView tvActualValue = ViewBindings.findChildViewById(rootView, id);
      if (tvActualValue == null) {
        break missingId;
      }

      id = R.id.tvActualWords;
      TextView tvActualWords = ViewBindings.findChildViewById(rootView, id);
      if (tvActualWords == null) {
        break missingId;
      }

      id = R.id.tvHouseType;
      TextView tvHouseType = ViewBindings.findChildViewById(rootView, id);
      if (tvHouseType == null) {
        break missingId;
      }

      id = R.id.tvInformation;
      TextView tvInformation = ViewBindings.findChildViewById(rootView, id);
      if (tvInformation == null) {
        break missingId;
      }

      id = R.id.tvPredictedValue;
      TextView tvPredictedValue = ViewBindings.findChildViewById(rootView, id);
      if (tvPredictedValue == null) {
        break missingId;
      }

      id = R.id.tvPredictedWords;
      TextView tvPredictedWords = ViewBindings.findChildViewById(rootView, id);
      if (tvPredictedWords == null) {
        break missingId;
      }

      return new FragmentHomeBinding((ConstraintLayout) rootView, clActual, clExtra, clPredicted,
          fragmentBarChart, textUserHint, textUserName, tvActualValue, tvActualWords, tvHouseType,
          tvInformation, tvPredictedValue, tvPredictedWords);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
