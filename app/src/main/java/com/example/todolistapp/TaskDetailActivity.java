package com.example.todolistapp;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");

        titleTextView.setText(title);
        descriptionTextView.setText(description);
    }
}
