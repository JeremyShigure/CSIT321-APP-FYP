package com.example.aquafinaapp.ui.faq;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FAQViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FAQViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("View FAQ");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
