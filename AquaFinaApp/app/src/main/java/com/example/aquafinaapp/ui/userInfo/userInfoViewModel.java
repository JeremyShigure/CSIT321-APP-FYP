package com.example.aquafinaapp.ui.userInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class userInfoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public userInfoViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("User Information");
    }

    public LiveData<String> getText() {
        return mText;
    }
}