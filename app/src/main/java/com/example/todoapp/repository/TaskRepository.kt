package com.example.todoapp.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.data.TaskDao
import com.example.todoapp.model.TaskToDo

class TaskRepository(private val taskDao: TaskDao) {

    val readAllData: LiveData<List<TaskToDo>> = taskDao.readAllData()

    suspend fun addTask(task: TaskToDo){
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: TaskToDo){
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: TaskToDo){
        taskDao.deleteTask(task)
    }

    suspend fun deleteAllUsers(){
        taskDao.deleteAllTasks()
    }


}