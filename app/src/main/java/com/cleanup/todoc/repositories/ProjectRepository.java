package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

/**
 * Created by Steve LEROY on 12/20/20.
 */
public class ProjectRepository {

    private final ProjectDao projectDao;

    public ProjectRepository(ProjectDao projectDao) { this.projectDao = projectDao; }

    // --- GET PROJECT ---

    public LiveData<List<Project>> getProjectList() { return this.projectDao.getProjectList(); }

}
