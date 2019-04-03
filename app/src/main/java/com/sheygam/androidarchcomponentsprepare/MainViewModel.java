package com.sheygam.androidarchcomponentsprepare;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<ContactEntity>> liveData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        liveData = DatabaseProvider.getInstance(getApplication())
                .contactDao()
                .getAllContacts();
    }

    public LiveData<List<ContactEntity>> getContacts(){
        return liveData;
    }
}
