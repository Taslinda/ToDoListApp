package com.example.todolistapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolistapp.Database.TaskRepository

class TaskViewModelFactory(private val taskRepository: TaskRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TaskViewModel::class.java)){
            return  TaskViewModel(repository) as T
        }
            throw IllegalAccessException("Unknown View Model class")
    }
}