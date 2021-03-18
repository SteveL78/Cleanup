package com.cleanup.todoc;

import android.content.ContentValues;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.cleanup.todoc.database.CleanUpDatabase;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Steve LEROY on 2/14/21.
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class ProjectDaoTest {

    private CleanUpDatabase database;

    // DATA SET FOR TEST
    private static Project PROJECT_DEMO = new Project(1, "FirstProjectTest", 0xFFFFFF);
    private static Project PROJECT_DEMO2 = new Project(2, "SecondProjectTest", 0xFFFFFF);
    private static Project PROJECT_DEMO3 = new Project(3, "ThirdProjectTest", 0xFFFFFF);


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                CleanUpDatabase.class)
                .allowMainThreadQueries()
                .addCallback(prepopulateDatabase())
                .build();
    }


    private static RoomDatabase.Callback prepopulateDatabase() {
        return new RoomDatabase.Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                prepopulateWithProjects(db);

            }
        };
    }

    private static void prepopulateWithProjects(SupportSQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", PROJECT_DEMO.getId());   // Je récupère l'Id du projet ...
        contentValues.put("name", PROJECT_DEMO.getName());   // ... ainsi que son nom ...
        contentValues.put("color", PROJECT_DEMO.getColor());  //... et sa couleur...
        db.insert("Project", OnConflictStrategy.IGNORE, contentValues); // ... et je l'insère ou l'ignore si déjà existant.

        contentValues.put("id", PROJECT_DEMO2.getId());   // Je récupère l'Id du projet ...
        contentValues.put("name", PROJECT_DEMO2.getName());   // ... ainsi que son nom ...
        contentValues.put("color", PROJECT_DEMO2.getColor());  //... et sa couleur...
        db.insert("Project", OnConflictStrategy.IGNORE, contentValues); // ... et je l'insère ou l'ignore si déjà existant.

        contentValues.put("id", PROJECT_DEMO3.getId());   // Je récupère l'Id du projet ...
        contentValues.put("name", PROJECT_DEMO3.getName());   // ... ainsi que son nom ...
        contentValues.put("color", PROJECT_DEMO3.getColor());  //... et sa couleur...
        db.insert("Project", OnConflictStrategy.IGNORE, contentValues); // ... et je l'insère ou l'ignore si déjà existant.

    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }


    @Test
    public void test_projects() throws InterruptedException {

        List<Project> projectList = LiveDataTestUtil.getValue(this.database.projectDao().getProjectList());
        assertEquals("FirstProjectTest", projectList.get(0).getName());
        assertEquals("SecondProjectTest", projectList.get(1).getName());
        assertEquals("ThirdProjectTest", projectList.get(2).getName());
    }

}