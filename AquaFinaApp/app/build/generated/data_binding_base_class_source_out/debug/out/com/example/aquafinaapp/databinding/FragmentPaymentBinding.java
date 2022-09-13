// Generated by view binder compiler. Do not edit!
package com.example.aquafinaapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.aquafinaapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentPaymentBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnBillDetails;

  @NonNull
  public final Button btnViewInvoice;

  @NonNull
  public final TextView tvBillAmount;

  @NonNull
  public final TextView tvDollarSign;

  @NonNull
  public final TextView tvInvoiceID;

  @NonNull
  public final TextView tvPaymentPageWords;

  @NonNull
  public final TextView tvShowInvoice;

  @NonNull
  public final TextView tvTotalAmt;

  private FragmentPaymentBinding(@NonNull LinearLayout rootView, @NonNull Button btnBillDetails,
      @NonNull Button btnViewInvoice, @NonNull TextView tvBillAmount,
      @NonNull TextView tvDollarSign, @NonNull TextView tvInvoiceID,
      @NonNull TextView tvPaymentPageWords, @NonNull TextView tvShowInvoice,
      @NonNull TextView tvTotalAmt) {
    this.rootView = rootView;
    this.btnBillDetails = btnBillDetails;
    this.btnViewInvoice = btnViewInvoice;
    this.tvBillAmount = tvBillAmount;
    this.tvDollarSign = tvDollarSign;
    this.tvInvoiceID = tvInvoiceID;
    this.tvPaymentPageWords = tvPaymentPageWords;
    this.tvShowInvoice = tvShowInvoice;
    this.tvTotalAmt = tvTotalAmt;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentPaymentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentPaymentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_payment, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentPaymentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnBillDetails;
      Button btnBillDetails = ViewBindings.findChildViewById(rootView, id);
      if (btnBillDetails == null) {
        break missingId;
      }

      id = R.id.btnViewInvoice;
      Button btnViewInvoice = ViewBindings.findChildViewById(rootView, id);
      if (btnViewInvoice == null) {
        break missingId;
      }

      id = R.id.tvBillAmount;
      TextView tvBillAmount = ViewBindings.findChildViewById(rootView, id);
      if (tvBillAmount == null) {
        break missingId;
      }

      id = R.id.tvDollarSign;
      TextView tvDollarSign = ViewBindings.findChildViewById(rootView, id);
      if (tvDollarSign == null) {
        break missingId;
      }

      id = R.id.tvInvoiceID;
      TextView tvInvoiceID = ViewBindings.findChildViewById(rootView, id);
      if (tvInvoiceID == null) {
        break missingId;
      }

      id = R.id.tvPaymentPageWords;
      TextView tvPaymentPageWords = ViewBindings.findChildViewById(rootView, id);
      if (tvPaymentPageWords == null) {
        break missingId;
      }

      id = R.id.tvShowInvoice;
      TextView tvShowInvoice = ViewBindings.findChildViewById(rootView, id);
      if (tvShowInvoice == null) {
        break missingId;
      }

      id = R.id.tvTotalAmt;
      TextView tvTotalAmt = ViewBindings.findChildViewById(rootView, id);
      if (tvTotalAmt == null) {
        break missingId;
      }

      return new FragmentPaymentBinding((LinearLayout) rootView, btnBillDetails, btnViewInvoice,
          tvBillAmount, tvDollarSign, tvInvoiceID, tvPaymentPageWords, tvShowInvoice, tvTotalAmt);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
