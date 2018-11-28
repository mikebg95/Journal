package com.example.mike.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        String mood = intent.getStringExtra("mood");
        String timestamp = intent.getStringExtra("timestamp");

        TextView titleView = findViewById(R.id.title);
        TextView contentView = findViewById(R.id.content);
        TextView moodView = findViewById(R.id.mood);
        TextView timestampView = findViewById(R.id.timestamp);

        titleView.setText(title);
        contentView.setText(content);
        moodView.setText(mood);
        timestampView.setText(timestamp);
    }
}
