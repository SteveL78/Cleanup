package com.cleanup.todoc;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.database.CleanUpDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Steve LEROY on 2/14/21.
 */
@RunWith(AndroidJUnit4.class)
class TaskDaoTest {

    // DATA SET FOR TEST
 /*   private static long TASK_ID = 1;
    private static long PROJECT_ID = 1;
    private static Project PROJECT_DEMO = new Project(PROJECT_ID, "FirstProjectTest", 0xFFFFFF);
    private static Task TASK_DEMO = new Task(TASK_ID, PROJECT_DEMO.getId(), "TaskTest1", new Date().getTime());
    private static Task NEW_TASK_DEMO = new Task(TASK_ID, PROJECT_DEMO.getId(), "TaskTest2", new Date().getTime());

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private CleanUpDatabase database;


    @Test
    public void insertAndGetTask() throws InterruptedException {
        // BEFORE : Adding a new task
        this.database.taskDao().insertTask(TASK_DEMO);
        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTaskList());
        Assert.assertTrue(tasks.get(0).getId() == TASK_DEMO.getId()
                && tasks.get(0).getName().equals(TASK_DEMO.getName())
                && tasks.get(0).getProjectId() == TASK_DEMO.getProjectId()
                && tasks.get(0).getCreationTimestamp() == TASK_DEMO.getCreationTimestamp());
    }


    @Test
    public void getTasksWhenNoTaskInserted() throws InterruptedException {
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTaskList());
        Assert.assertTrue(tasks.isEmpty());
    }


    @Test
    public void insertAndUpdateTask() throws InterruptedException {
        // BEFORE : Adding demo project & demo taskList. Next, update item added & re-save it
        this.database.taskDao().insertTask(TASK_DEMO);
        this.database.taskDao().insertTask(NEW_TASK_DEMO);
        Task taskAdded = LiveDataTestUtil.getValue(this.database.taskDao().getTaskList(TASK_ID)).get(0);
        taskAdded.setSelected(true);
        this.database.taskDao().updateTask(taskAdded);

        //TEST
        List<Task> taskList = LiveDataTestUtil.getValue(this.database.taskDao().getTaskList(TASK_ID));
        assertTrue(taskList.size() == 1 && taskList.get(0).getSelected());
    }

*/
}
