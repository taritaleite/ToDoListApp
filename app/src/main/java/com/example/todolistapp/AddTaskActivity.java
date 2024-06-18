package com.example.todolistapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    private EditText titleEditText, descriptionEditText;
    private DataBaseHelper dataBaseHelper;
    private int taskId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        Button saveButton = findViewById(R.id.saveButton);

        dataBaseHelper = new DataBaseHelper(this);

        // Obtém os dados da tarefa para edição, se existirem
        if (getIntent().hasExtra("id")) {
            taskId = getIntent().getIntExtra("id", -1);
            String title = getIntent().getStringExtra("title");
            String description = getIntent().getStringExtra("description");

            titleEditText.setText(title);
            descriptionEditText.setText(description);
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString().trim();
                String description = descriptionEditText.getText().toString().trim();

                if (!title.isEmpty() && !description.isEmpty()) {
                    if (taskId == -1) {
                        // Adicionar nova tarefa
                        dataBaseHelper.addTask(new Task(title, description));
                        Toast.makeText(AddTaskActivity.this, "Task added", Toast.LENGTH_SHORT).show();
                    } else {
                        // Editar tarefa existente
                        dataBaseHelper.updateTask(new Task(taskId, title, description));
                        Toast.makeText(AddTaskActivity.this, "Task updated", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                } else {
                    Toast.makeText(AddTaskActivity.this, "Please enter title and description", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
