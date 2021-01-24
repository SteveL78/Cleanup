package com.cleanup.todoc.database;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.Date;

/**
 * Created by Steve LEROY on 11/28/20.
 */
@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)
public abstract class CleanUpDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile CleanUpDatabase INSTANCE;

    // --- DAO ---
    public abstract ProjectDao projectDao();
    public abstract TaskDao taskDao();

    // --- INSTANCE ---
    public static CleanUpDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CleanUpDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CleanUpDatabase.class, "MyDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // PREPOPULATE

    private static Callback prepopulateDatabase() {
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                prepopulateWithProjects(db);
                prepopulateWithTasks(db);
            }
        };
    }

    private static void prepopulateWithProjects(SupportSQLiteDatabase db) {
        Project[] allProjects = new Project[]{
                new Project(1, "Projet Tartampion", 0xFFEADAD1),
                new Project(2, "Projet Lucidia", 0xFFB4CDBA),
                new Project(3, "Projet Circus", 0xFFA3CED2),
        };

        for (Project project : allProjects) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", project.getId());   // Je récupère l'Id du projet ...
            contentValues.put("name", project.getName());   // ... ainsi que son nom ...
            contentValues.put("color", project.getColor());  //... et sa couleur...
            db.insert("Project", OnConflictStrategy.IGNORE, contentValues); // ... et je l'insère ou l'ignore si déjà existant.
        }
    }

    private static void prepopulateWithTasks(SupportSQLiteDatabase db) {
        Task[] allTasks = new Task[]{
                new Task(1, 1, "task 1", new Date().getTime()),
                new Task(2, 2, "task 2", new Date().getTime()),
                new Task(3, 3, "task 3", new Date().getTime()),
                new Task(4, 3, "task 4", new Date().getTime())
        };


        for (Task task : allTasks) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", task.getId());   // Je récupère l'Id du Task selon sa position ...
            contentValues.put("projectId", task.getProjectId());   // ... ainsi que l'Id du projet associé ...
            contentValues.put("name", task.getName());  //... et son nom ...
            contentValues.put("creationTimestamp", task.getCreationTimestamp());  //... et son heure de création ...
            db.insert("Task", OnConflictStrategy.IGNORE, contentValues); // ... et je l'insère ou l'ignore si déjà existant.
        }
    }
}