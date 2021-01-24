package com.cleanup.todoc.injections;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.repositories.ProjectRepository;
import com.cleanup.todoc.repositories.TaskRepository;
import com.cleanup.todoc.ui.MainViewModel;

import java.util.concurrent.Executor;

/**
 * Created by Steve LEROY on 1/24/21.
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ProjectRepository projectDataSource;
    private final TaskRepository taskDataSource;
    private final Executor executor;

    public ViewModelFactory(ProjectRepository projectDataSource, TaskRepository taskDataSource, Executor executor) {
        this.projectDataSource = projectDataSource;
        this.taskDataSource = taskDataSource;
        this.executor = executor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(projectDataSource, taskDataSource, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
