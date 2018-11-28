package com.example.mike.journal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.DropBoxManager;
import android.renderscript.ScriptGroup;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Map;
import java.lang.Object;

public class EntryDatabase extends SQLiteOpenHelper {

    // create constant variables for sqlite to work with
    public static final String DATABASE_NAME = "myEntries";
    public static final String COLUMN_NAME_ID = "_id";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_CONTENT = "content";
    public static final String COLUMN_NAME_MOOD = "mood";
    public static final String COLUMN_NAME_TIMESTAMP = "timestamp";

    private static EntryDatabase instance;

    // constructor for the entrydatabase
    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // returns the instance (and creates a new one if instance is null)
    public static EntryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new EntryDatabase(context, DATABASE_NAME, null, 1);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table with relevant columns
        db.execSQL("create table " + DATABASE_NAME + "(" + COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME_TITLE + " TEXT, " + COLUMN_NAME_CONTENT +  " TEXT, " + COLUMN_NAME_MOOD +  " TEXT, " + COLUMN_NAME_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP)");

        // add some sample items to table
        db.execSQL("INSERT INTO " + DATABASE_NAME + "(" + COLUMN_NAME_TITLE + ", " + COLUMN_NAME_CONTENT + ", " + COLUMN_NAME_MOOD + ") VALUES ('studying', 'today i am programming', 'neutral')");
        db.execSQL("INSERT INTO " + DATABASE_NAME + "(" + COLUMN_NAME_TITLE + ", " + COLUMN_NAME_CONTENT + ", " + COLUMN_NAME_MOOD + ") VALUES ('chilling', 'today i am getting high', 'happy')");
        db.execSQL("INSERT INTO " + DATABASE_NAME + "(" + COLUMN_NAME_TITLE + ", " + COLUMN_NAME_CONTENT + ", " + COLUMN_NAME_MOOD + ") VALUES ('working out', 'today i am kickboxing', 'focused')");
        db.execSQL("INSERT INTO " + DATABASE_NAME + "(" + COLUMN_NAME_TITLE + ", " + COLUMN_NAME_CONTENT + ", " + COLUMN_NAME_MOOD + ") VALUES ('reading', 'today i am reading about politics', 'relaxed')");
    }

    // when database is upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // delete table and create new one
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }

    // returns all information from database
    public Cursor selectAll() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor =  db.rawQuery("SELECT * FROM " + DATABASE_NAME,null);
        return cursor;
    }

    // insert given entry in database
    public void insert(JournalEntry entry) {
        Log.d("???", "???");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", entry.getTitle());
        cv.put("content", entry.getContent());
        cv.put("mood", entry.getMood());
        db.insert(DATABASE_NAME, null, cv);
        db.close();
    }

    // delete given entry from database
    public void delete(long id) {
        Log.d("id in delete function", Long.toString(id));


        SQLiteDatabase db = getWritableDatabase();
//        String table = DATABASE_NAME;
//        String whereClause = COLUMN_NAME_ID + "=?";
//        String[] whereArgs = new String[] { String.valueOf(id) };
        db.delete(DATABASE_NAME, COLUMN_NAME_ID + "=?", new String[] { String.valueOf(id) });

//        SQLiteDatabase db = getWritableDatabase();
//        db.delete
//        db.rawQuery("DELETE * FROM " + DATABASE_NAME + " WHERE " + COLUMN_NAME_ID + " = " + id, null).moveToFirst();
    }
}
