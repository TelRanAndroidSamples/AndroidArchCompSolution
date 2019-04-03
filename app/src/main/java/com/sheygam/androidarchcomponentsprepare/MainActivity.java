package com.sheygam.androidarchcomponentsprepare;



import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;


public class MainActivity extends AppCompatActivity implements ContactAdapter.ItemClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.myRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ContactAdapter(this,this);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration divider = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        recyclerView.addItemDecoration(divider);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Todo Implement delete item logic
            }
        }).attachToRecyclerView(recyclerView);


        FloatingActionButton fab = findViewById(R.id.fabAddContact);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,AddContactActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Todo Implement load date from database
        List<ContactEntity> fakeList = new ArrayList<>();
        fakeList.add(new ContactEntity("Vasya","0556788990",new Date()));
        adapter.setContacts(fakeList);
    }

    @Override
    public void onItemClick(int itemId) {
        Intent intent = new Intent(this, AddContactActivity.class);
        intent.putExtra(AddContactActivity.EXTRA_KEY,itemId);
        startActivity(intent);
    }
}
