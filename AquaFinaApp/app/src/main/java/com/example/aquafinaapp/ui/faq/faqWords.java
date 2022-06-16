package com.example.aquafinaapp.ui.faq;

import android.text.TextPaint;

public class faqWords {

    private String faqQuestions, faqWords;
    private boolean expandable;

    public faqWords(String faqQuestions, String faqWords) {
        this.faqQuestions = faqQuestions;
        this.faqWords = faqWords;
        this.expandable = false;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public String getFaqQuestions() {
        return faqQuestions;
    }

    public void setFaqQuestions(String faqQuestions) {
        this.faqQuestions = faqQuestions;
    }

    public String getFaqWords() {
        return faqWords;
    }

    public void setFaqWords(String faqWords) {
        this.faqWords = faqWords;
    }

    @Override
    public String toString() {
        return "faqWords{" +
                "faqQuestions='" + faqQuestions + '\'' +
                ", faqWords='" + faqWords + '\'' +
                '}';
    }
}
