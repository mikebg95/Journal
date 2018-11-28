package com.example.mike.journal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.renderscript.ScriptGroup;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Adapter;


public class MainActivity extends AppCompatActivity {

    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create variables for the listview and floating action button
        ListView entries = findViewById(R.id.entries);
        FloatingActionButton newEntry = findViewById(R.id.newEntry);

        Context context = getApplicationContext();
        Cursor cursor = EntryDatabase.getInstance(context).selectAll();
        adapter = new EntryAdapter(context, cursor);
        entries.setAdapter(adapter);

        newEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingButtonClicked(v);
            }
        });

        entries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                String title = cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_NAME_TITLE));
                String content = cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_NAME_CONTENT));
                String mood = cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_NAME_MOOD));
                String timestamp = cursor.getString(cursor.getColumnIndex(EntryDatabase.COLUMN_NAME_TIMESTAMP));


                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);

                intent.putExtra("title", title);
                intent.putExtra("content", content);
                intent.putExtra("mood", mood);
                intent.putExtra("timestamp", timestamp);

                startActivity(intent);
            }
        });

        entries.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("longclick", "Longclicked");
                // get entrydatabase
                db = EntryDatabase.getInstance(getApplicationContext());

                // retrieve which item was clicked on
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);

                // get id of that entry item
                long entryId = cursor.getLong(cursor.getColumnIndex(EntryDatabase.COLUMN_NAME_ID));
                Log.d("entryId", Long.toString(entryId));

                // call delete function in database
                db.delete(entryId);
                updateData();
                return true;
            }
        });

        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
    }

    public void floatingButtonClicked(View v) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    private void updateData() {
        Context context = getApplicationContext();
        Cursor cursor = EntryDatabase.getInstance(context).selectAll();
        adapter.swapCursor(cursor);
    }
}
