package com.uxr.demo.one;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.uxr.demo.one.databinding.ItemTaskBinding;
import com.uxr.demo.one.models.Task;

import java.util.List;

/**
 * Created by abigail on 6/6/2016.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> mTasks;

    public TaskAdapter(List<Task> tasks) {
        mTasks = tasks;
    }

    public void setNewList(List<Task> tasks) {
        //reuses the same list to avoid recreation of object
        mTasks.clear();
        mTasks.addAll(tasks);

        //should refresh UI everytime there's a change in data
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTaskBinding binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        ViewHolder vh = new ViewHolder(binding);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Task task = mTasks.get(position);
        holder.binding.setTask(task);
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ItemTaskBinding binding;

        public ViewHolder(ItemTaskBinding v) {
            super(v.getRoot());
            binding = v;
        }
    }
}
