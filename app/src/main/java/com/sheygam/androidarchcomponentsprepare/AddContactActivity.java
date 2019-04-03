package com.sheygam.androidarchcomponentsprepare;



import android.content.Intent;
import android.os.Bundle;
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

        if(savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_KEY)){
            currContactId = savedInstanceState.getInt(INSTANCE_KEY, DEFAULT_CNT_ID);
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_KEY)){
            saveBtn.setText("Update");
            if(currContactId == DEFAULT_CNT_ID){
                currContactId = intent.getIntExtra(EXTRA_KEY,DEFAULT_CNT_ID);
                //Todo load from database contact
                //Todo Update ui
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

        finish();
    }
}
