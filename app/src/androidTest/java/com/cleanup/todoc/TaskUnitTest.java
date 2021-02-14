package com.cleanup.todoc;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.cleanup.todoc.database.CleanUpDatabase;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Steve LEROY on 2/14/21.
 */
@RunWith(JUnit4.class)
class TaskUnitTest {

    private TaskDao mTaskDao;
    private ProjectDao mProjectDao;
    private CleanUpDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, CleanUpDatabase.class).build();
        mTaskDao = db.taskDao();
        mProjectDao = db.projectDao();

    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void test_projects() throws InterruptedException {

        LiveData<List<Project>> p = mProjectDao.getProjectList();

        List<Project> projectList = LiveDataTestUtil.getValue(p);

        assertEquals("Projet Tartampion", projectList.get(0).getName());
        assertEquals("Projet Lucidia", projectList.get(1).getName());
        assertEquals("Projet Circus", projectList.get(2).getName());

    }
}
