package com.istef.sqlite_contactmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.istef.sqlite_contactmanager.model.Contact;

import static com.istef.sqlite_contactmanager.util.Config.*;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {

    public DBManager(@Nullable Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + DB_TABLE_NAME + '(' +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_NAME + " TEXT," +
                KEY_PHONE_NUMBER + " TEXT UNIQUE)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + DB_TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }


    // CRUD
    public long addContact(Contact contact) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, contact.getName());
            values.put(KEY_PHONE_NUMBER, contact.getPhoneNumber());

            return db.insert(DB_TABLE_NAME, null, values);
        }
    }

    public Contact getContact(long id) {
        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.query(
                     DB_TABLE_NAME,
                     new String[]{KEY_ID, KEY_NAME, KEY_PHONE_NUMBER},
                     KEY_ID + "=?",
                     new String[]{String.valueOf(id)},
                     null, null, null)
        ) {
            return !cursor.moveToFirst() ? null : new Contact(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2));
        }
    }

    public List<Contact> getAllContacts() {
        String SELECT_ALL = "SELECT * FROM " + DB_TABLE_NAME;

        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.rawQuery(SELECT_ALL, null)
        ) {
            List<Contact> contactList = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    Contact contact = new Contact(
                            cursor.getLong(0),
                            cursor.getString(1),
                            cursor.getString(2));
                    contactList.add(contact);
                } while (cursor.moveToNext());
            }

            return contactList;
        }
    }

    public int updateContact(Contact contact) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, contact.getName());
            values.put(KEY_PHONE_NUMBER, contact.getPhoneNumber());

            return db.update(
                    DB_TABLE_NAME,
                    values,
                    KEY_ID + "=?",
                    new String[]{String.valueOf(contact.getId())});
        }
    }

    public int deleteContact(Contact contact) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            return db.delete(DB_TABLE_NAME,
                    KEY_ID + "=?",
                    new String[]{String.valueOf(contact.getId())});
        }
    }

    public int contactCount() {
        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE_NAME, null)
        ) {
            return cursor.getCount();
        }
    }

}
