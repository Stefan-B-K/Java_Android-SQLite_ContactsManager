package com.istef.sqlite_contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ContactDetailsActivity extends AppCompatActivity {
    private TextView detailName, detailPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        detailName = findViewById(R.id.txtDetailName);
        detailPhone = findViewById(R.id.txtDetailPhone);

        detailName.setText(getIntent().getStringExtra("name"));
        detailPhone.setText(getIntent().getStringExtra("phone"));

    }
}