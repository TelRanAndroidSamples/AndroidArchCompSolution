package com.sheygam.androidarchcomponentsprepare;



import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import java.util.Date;

public class AddContactActivity extends AppCompatActivity {

    private static final int DEFAULT_CNT_ID = -1;
    private static final String INSTANCE_KEY = "instanceKey";
    public static final String EXTRA_KEY = "extraKey";

    private EditText inputName;
    private EditText inputPhone;
    private CalendarView calendar;

    private int currContactId = DEFAULT_CNT_ID;
    private DatabaseProvider database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        inputName = findViewById(R.id.inputName);
        inputPhone = findViewById(R.id.inputPhone);
        calendar = findViewById(R.id.calendarView);

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(v -> {
            onSaveButtonClicked();
        });

        database = DatabaseProvider.getInstance(getApplicationContext());

        if(savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_KEY)){
            currContactId = savedInstanceState.getInt(INSTANCE_KEY, DEFAULT_CNT_ID);
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_KEY)){
            saveBtn.setText("Update");
            if(currContactId == DEFAULT_CNT_ID){
                currContactId = intent.getIntExtra(EXTRA_KEY,DEFAULT_CNT_ID);
                AddContactViewModelFactory factory = new AddContactViewModelFactory(database,currContactId);
                AddContactViewModel viewModel = ViewModelProviders.of(this).get(AddContactViewModel.class);
                viewModel.getContact().observe(this, new Observer<ContactEntity>() {
                    @Override
                    public void onChanged(@Nullable ContactEntity entity) {
                        viewModel.getContact().removeObserver(this);
                        updateUI(entity);
                    }
                });
            }
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(INSTANCE_KEY,currContactId);
        super.onSaveInstanceState(outState);
    }

    private void updateUI(ContactEntity contact){
        if (contact == null) {
            return;
        }
        inputName.setText(contact.getName());
        inputPhone.setText(contact.getPhone());
        calendar.setDate(contact.getBirthDay().getTime());
    }

    private void onSaveButtonClicked() {
        String name = String.valueOf(inputName.getText());
        String phone = String.valueOf(inputPhone.getText());
        Date bDay = new Date(calendar.getDate());

        ContactEntity entity = new ContactEntity(name,phone,bDay);

        ThreadSchedulers.getInstance().diskIO().execute(() -> {
            if(currContactId == DEFAULT_CNT_ID) {
                database.contactDao().addContact(entity);
            }else{
                entity.setId(currContactId);
                database.contactDao().updateContact(entity);
            }
            finish();
        });
    }
}
