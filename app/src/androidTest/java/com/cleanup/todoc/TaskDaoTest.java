package com.cleanup.todoc;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.cleanup.todoc.database.CleanUpDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Steve LEROY on 2/14/21.
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class TaskDaoTest {

    // FOR DATA
    private CleanUpDatabase database;

    // DATA SET FOR TEST
    private static long TASK_ID = 1;
    private static long NEW_TASK_ID = 31;
    private static long PROJECT_ID = 1;
    private static Project PROJECT_DEMO = new Project(PROJECT_ID, "FirstProjectTest", 0xFFFFFF);
    private static Task TASK_DEMO = new Task(TASK_ID, PROJECT_DEMO.getId(), "TaskTest1", new Date().getTime());
    private static Task NEW_TASK_DEMO = new Task(TASK_ID, PROJECT_DEMO.getId(), "TaskTest2", new Date().getTime());

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                CleanUpDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void getTasksWhenNoItemInserted() throws InterruptedException {
        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTaskList());
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void insertAndGetTasks() throws InterruptedException {
        this.database.taskDao().insertTask(TASK_DEMO);
        //TEST
        List<Task> taskList = LiveDataTestUtil.getValue(this.database.taskDao().getTaskList());
        assertTrue(taskList.get(0).getId() == TASK_DEMO.getId()
                && taskList.get(0).getName().equals(TASK_DEMO.getName())
                && taskList.get(0).getProjectId() == TASK_DEMO.getProjectId()
                && taskList.get(0).getCreationTimestamp() == TASK_DEMO.getCreationTimestamp());
    }

    @Test
    public void insertAndUpdateTask() throws InterruptedException {
        this.database.taskDao().insertTask(TASK_DEMO);
        TASK_DEMO.setId(NEW_TASK_ID);
        this.database.taskDao().updateTask(TASK_DEMO);
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTaskList());
        assertEquals(NEW_TASK_ID, tasks.get(0).getId());
    }

    @Test
    public void insertAndDeleteTask() throws InterruptedException {
        this.database.taskDao().insertTask(TASK_DEMO);
        this.database.taskDao().deleteTask(TASK_ID);
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTaskList());
        assertTrue(tasks.isEmpty());
    }
}