package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;

/**
 * Created by Steve LEROY on 11/28/20.
 */
@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task")
    List<Task> getTaskList();



   /* @Query("SELECT * FROM Task WHERE projectId = :projectId")
    LiveData<List<Task>> getTaskList(long projectId);*/

    @Insert
    long insertTask(Task item);

    @Update
    int updateTask(Task item);

    @Query("DELETE FROM Task WHERE id = :taskId")
    int deleteTask(long taskId);

}