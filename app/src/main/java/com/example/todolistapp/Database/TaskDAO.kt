package com.example.todolistapp.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDAO {

    @Insert
    suspend fun insertTask(task: Task): Long

    @Update
    suspend fun updateTask(task: Task): Int

    @Delete
    suspend fun deleteTask(task: Task): Int

    @Query( "DELETE FROM task_data_table")
    suspend fun deleteAll()

    @Query( "SELECT * FROM task_data_table")
    fun getAllTasks():LiveData<List<Task>>



}