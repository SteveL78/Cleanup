package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

/**
 * Created by Steve LEROY on 12/20/20.
 */
public class TaskRepository {

    private final TaskDao taskDao;

    public TaskRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    // --- GET ---
    public LiveData<List<Task>> getTaskList() {
        return this.taskDao.getTaskList();
    }

    // --- CREATE ---
    public void createTask(Task task) {
        taskDao.insertTask(task);
    }

    // --- DELETE ---
    public void deleteTask(long taskId) {
        taskDao.deleteTask(taskId);
    }

    // --- UPDATE ---
    public void updateTask(Task task) {
        taskDao.updateTask(task);
    }

}
