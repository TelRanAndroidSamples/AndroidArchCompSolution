package com.sheygam.androidarchcomponentsprepare;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class AddContactViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private DatabaseProvider database;
    private int contactId;

    public AddContactViewModelFactory(DatabaseProvider database, int contactId) {
        this.database = database;
        this.contactId = contactId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddContactViewModel(database,contactId);
    }
}
