package com.istef.sqlite_contactmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.istef.sqlite_contactmanager.adapter.MainRecViewAdapter;
import com.istef.sqlite_contactmanager.data.DBManager;
import com.istef.sqlite_contactmanager.model.Contact;

import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private List<Contact> contactList;
    private RecyclerView recyclerView;
    private MainRecViewAdapter mainRecViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);                                 //      !!!
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try (DBManager dbManager = new DBManager(this)) {

            contactList = dbManager.getAllContacts();
            mainRecViewAdapter = new MainRecViewAdapter(this, contactList);
            recyclerView.setAdapter(mainRecViewAdapter);

//            Log.d("CONTACTS", "::::: " + contactList.toString());
//            Log.d("CONTACT COUNT", "::::: " + dbManager.contactCount());
        }

    }
}