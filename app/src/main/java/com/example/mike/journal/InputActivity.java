package com.example.mike.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Map;

public class InputActivity extends AppCompatActivity {

    public String mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // create variable linked to submit button and emoji's
        Button submitButton = findViewById(R.id.submitButton);
        ImageButton happy = findViewById(R.id.happy);
        ImageButton sad = findViewById(R.id.sad);
        ImageButton neutral = findViewById(R.id.neutral);
        ImageButton angry = findViewById(R.id.angry);

        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "happy";
            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "sad";
            }
        });

        neutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "neutral";
            }
        });

        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "angry";
            }
        });



        // when clicked on submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("clicked", "Submit button clicked");

                // create variables linked to title and content edittext
                EditText titleInput = findViewById(R.id.title);
                EditText contentInput = findViewById(R.id.content);

                // convert text from title and content to string variables
                String title = titleInput.getText().toString();
                String content = contentInput.getText().toString();


                // create new entry with text and title
                JournalEntry entry = new JournalEntry(title, content, mood);
                addEntry(entry);

                // go back to mainactivity
                Intent intent = new Intent(InputActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addEntry(JournalEntry entry) {
        EntryDatabase.getInstance(getApplicationContext()).insert(entry);

    }
}
