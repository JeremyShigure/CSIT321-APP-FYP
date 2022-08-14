package com.example.aquafinaapp.ui.waterSavingTips;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aquafinaapp.R;
import com.example.aquafinaapp.ui.faq.faqWords;

import java.util.List;

public class waterSavingTipsAdapter extends RecyclerView.Adapter<waterSavingTipsAdapter.tipsVH> {

    List<waterSavingTipsWords> tipsQuestionsList;

    public waterSavingTipsAdapter(List<waterSavingTipsWords> tipsQuestionsList) {
        this.tipsQuestionsList = tipsQuestionsList;
    }

    @NonNull
    @Override
    public tipsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tips_row, parent, false);
        return new tipsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull tipsVH holder, int position) {
        waterSavingTipsWords wstWords = tipsQuestionsList.get(position);
        holder.tvTips.setText(wstWords.getTipsQuestions());
        holder.tvTipsWords.setText(wstWords.getTipsWords());

        boolean isExpandable = tipsQuestionsList.get(position).isExpandable();
        holder.tipsRelativeLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
    }


    @Override
    public int getItemCount() {
        return tipsQuestionsList.size();
    }

    public class tipsVH extends RecyclerView.ViewHolder {

        TextView tvTips, tvTipsWords;
        LinearLayout tipsLinearLayout;
        RelativeLayout tipsRelativeLayout;

        public tipsVH(@NonNull View itemView) {
            super(itemView);

            tvTips = itemView.findViewById(R.id.tvTips);
            tvTipsWords = itemView.findViewById(R.id.tvTipsWords);

            tipsLinearLayout = itemView.findViewById(R.id.tipsLinearLayout);
            tipsRelativeLayout = itemView.findViewById(R.id.tipsRelativeLayout);

            tipsLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    waterSavingTipsWords wstWords = tipsQuestionsList.get(getAdapterPosition());
                    wstWords.setExpandable(!wstWords.isExpandable());
                    notifyItemChanged(getAdapterPosition());

                }
            });

        }

    }

}
