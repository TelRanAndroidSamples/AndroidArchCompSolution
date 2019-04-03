package com.sheygam.androidarchcomponentsprepare;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

public class AddContactViewModel extends ViewModel {
    private LiveData<ContactEntity> liveData;

    public AddContactViewModel(DatabaseProvider database, int contactId) {
        liveData = database.contactDao().getContactById(contactId);
    }

    public LiveData<ContactEntity> getContact() {
        return liveData;
    }
}
