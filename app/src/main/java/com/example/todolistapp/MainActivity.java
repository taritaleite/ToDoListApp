package com.example.todolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskClickListener {

    private DataBaseHelper dataBaseHelper;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataBaseHelper = new DataBaseHelper(this);
        loadTasks();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTasks();
    }

    private void loadTasks() {
        List<Task> taskList = dataBaseHelper.getAllTasks();
        taskAdapter = new TaskAdapter(taskList, this);
        recyclerView.setAdapter(taskAdapter);
    }

    @Override
    public void onTaskEdit(int taskId, String title, String description) {
        Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
        intent.putExtra("id", taskId);
        intent.putExtra("title", title);
        intent.putExtra("description", description);
        startActivity(intent);
    }

    @Override
    public void onTaskDelete(int taskId) {
        dataBaseHelper.deleteTask(taskId);
        loadTasks();
        Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show();
    }
}
