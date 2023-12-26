package com.istef.sqlite_contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.istef.sqlite_contactmanager.data.DBManager;
import com.istef.sqlite_contactmanager.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try (DBManager dbManager = new DBManager(this)) {
//            Contact testContact = new Contact("Emil", "00001110000000");
//            long newId = dbManager.addContact(testContact);
//            Log.d("ADD NEW", "onCreate: " + newId);

//            Contact testContact = dbManager.getContact(1);
//            testContact.setName("Madonna");
//            testContact.setPhoneNumber("000 000 000");
//            dbManager.updateContact(testContact);

//            Contact testContact = dbManager.getContact(2);
//            dbManager.deleteContact(testContact);

//            dbManager.dropTable(Config.DB_TABLE_NAME);
            List<Contact> contactList = dbManager.getAllContacts();
            Log.d("CONTACTS", "::::: " + contactList.toString());
            Log.d("CONTACT COUNT", "::::: " + dbManager.contactCount());
        }

    }
}