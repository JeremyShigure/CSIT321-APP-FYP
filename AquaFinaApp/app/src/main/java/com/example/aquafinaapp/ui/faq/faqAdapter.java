package com.example.aquafinaapp.ui.faq;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aquafinaapp.R;

import java.util.List;

public class faqAdapter extends RecyclerView.Adapter<faqAdapter.faqVH> {

    List<faqWords> faqQuestionsList;

    public faqAdapter(List<faqWords> faqQuestionsList) {
        this.faqQuestionsList = faqQuestionsList;
    }

    @NonNull
    @Override
    public faqVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_row, parent, false);
        return new faqVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull faqVH holder, int position) {
        faqWords faqWords = faqQuestionsList.get(position);
        holder.tvFAQ.setText(faqWords.getFaqQuestions());
        holder.tvFAQWords.setText(faqWords.getFaqWords());

        boolean isExpandable = faqQuestionsList.get(position).isExpandable();
        holder.faqRelativeLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
    }


    @Override
    public int getItemCount() {
        return faqQuestionsList.size();
    }

    public class faqVH extends RecyclerView.ViewHolder {

        TextView tvFAQ, tvFAQWords;
        LinearLayout faqLinearLayout;
        RelativeLayout faqRelativeLayout;

        public faqVH(@NonNull View itemView) {
            super(itemView);

            tvFAQ = itemView.findViewById(R.id.tvFAQ);
            tvFAQWords = itemView.findViewById(R.id.tvFAQWords);

            faqLinearLayout = itemView.findViewById(R.id.faqLinearLayout);
            faqRelativeLayout = itemView.findViewById(R.id.faqRelativeLayout);

            faqLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    faqWords faqWords = faqQuestionsList.get(getAdapterPosition());
                    faqWords.setExpandable(!faqWords.isExpandable());
                    notifyItemChanged(getAdapterPosition());

                }
            });

        }

    }

}
