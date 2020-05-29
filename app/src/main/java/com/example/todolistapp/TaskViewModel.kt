package com.example.todolistapp

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolistapp.Database.Task
import com.example.todolistapp.Database.TaskRepository

class TaskViewModel(private val repository: TaskRepository) : ViewModel(),Observable{

    val tasks = repository.tasks
    private var isUpdateOrDelete = false
    private lateinit var taskToUpdateOrDelete: Task

    @Bindable
    val inputCreatedAt = MutableLiveData<String>()
    @Bindable
    val inputTaskName = MutableLiveData<String>()
    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()
    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"

    }

    fun saveOrUpdate(){
        val createdAt = inputCreatedAt.value!!
        val taskName = inputTaskName.value!!
        insert(Task(0.createdAt.taskName))
        inputCreatedAt.value = null
        inputTaskName.value = null

    }
    fun clearAllOrDelete(){
        clearAll()

    }

    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    fun update(task: Task) = viewModelScope.launch {
        repository.update(task)
    }

    fun delete(task: Task) = viewModelScope.launch {
        repository.delete(task)
    }

    fun clearAll(task: Task) = viewModelScope.launch {
        repository.deleteAll()
    }

    fun initUpdateAndDelete(task: Task){
        inputTaskName.value = task.createdAt
        inputTaskName.value = task.taskName
        isUpdateOrDelete = true
        taskToUpdateOrDelete = task
        saveOrUpdateButtonText.value = "Upadate"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.onPropertyChangedCallback?){

    }

    override fun addOnPropertyChangedCallback(callback: Observable.onPropertyChangedCallback?){

    }




}