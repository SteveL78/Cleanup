package com.cleanup.todoc.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectRepository;
import com.cleanup.todoc.repositories.TaskRepository;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by Steve LEROY on 1/24/21.
 */
public class MainViewModel extends ViewModel {

    // REPOSITORIES
    private final ProjectRepository projectDataSource;
    private final TaskRepository taskDataSource;
    private final Executor executor;


    // CONSTRUCTOR
    public MainViewModel(ProjectRepository projectDataSource, TaskRepository taskDataSource, Executor executor) {
        this.projectDataSource = projectDataSource;
        this.taskDataSource = taskDataSource;
        this.executor = executor;
    }


    // -------------
    // FOR PROJECT
    // -------------

    public LiveData<Project> getAllProject(long projectId) { return projectDataSource.getProject(projectId); }

    // -------------
    // FOR TASK
    // -------------

    public LiveData<List<Task>> getTasks() {
        return taskDataSource.getTaskList();
    }

    public void createTask(Task task) {
        executor.execute(() -> taskDataSource.createTask(task));
    }

    public void deleteTask(long taskId) {
        executor.execute(() -> taskDataSource.deleteTask(taskId));
    }

    public void updateTask(Task task) {
        executor.execute(() -> taskDataSource.updateTask(task));
    }
}
