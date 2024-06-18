package com.example.todolistapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;
    private OnTaskClickListener onTaskClickListener;

    public interface OnTaskClickListener {
        void onTaskEdit(int taskId, String title, String description);
        void onTaskDelete(int taskId);
    }

    public TaskAdapter(List<Task> taskList, OnTaskClickListener onTaskClickListener) {
        this.taskList = taskList;
        this.onTaskClickListener = onTaskClickListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.titleTextView.setText(task.getTitle());
        holder.descriptionTextView.setText(task.getDescription());

        holder.editImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTaskClickListener.onTaskEdit(task.getId(), task.getTitle(), task.getDescription());
            }
        });

        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTaskClickListener.onTaskDelete(task.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, descriptionTextView;
        ImageView editImageView, deleteImageView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            editImageView = itemView.findViewById(R.id.editImageView);
            deleteImageView = itemView.findViewById(R.id.deleteImageView);
        }
    }
}
