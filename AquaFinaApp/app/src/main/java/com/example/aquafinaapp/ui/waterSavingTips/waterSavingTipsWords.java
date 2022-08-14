package com.example.aquafinaapp.ui.waterSavingTips;

public class waterSavingTipsWords {

    private String tipsQuestions, tipsWords;
    private boolean expandable;

    public waterSavingTipsWords(String tipsQuestions, String tipsWords) {
        this.tipsQuestions = tipsQuestions;
        this.tipsWords = tipsWords;
        this.expandable = false;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public String getTipsQuestions() {
        return tipsQuestions;
    }

    public void setFaqQuestions(String faqQuestions) {
        this.tipsQuestions = faqQuestions;
    }

    public String getTipsWords() {
        return tipsWords;
    }

    public void setTipsWords(String faqWords) {
        this.tipsWords = tipsWords;
    }

    @Override
    public String toString() {
        return "faqWords{" +
                "faqQuestions='" + tipsQuestions + '\'' +
                ", faqWords='" + tipsWords + '\'' +
                '}';
    }
}
