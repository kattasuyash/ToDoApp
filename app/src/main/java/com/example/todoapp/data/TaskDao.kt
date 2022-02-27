package com.example.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoapp.model.TaskToDo

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun addTask(task: TaskToDo)
    fun addTask(task: TaskToDo)

    @Update
//    suspend fun updateTask(task: TaskToDo)
    fun updateTask(task: TaskToDo)

    @Delete
//    suspend fun deleteTask(task: TaskToDo)
    fun deleteTask(task: TaskToDo)

    @Query("DELETE FROM task_table")
//    suspend fun deleteAllTasks()
    fun deleteAllTasks()

    @Query("SELECT * FROM task_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<TaskToDo>>
}