package com.example.todolistapp

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
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

    private val statusMessage = MutableLiveData<Even<String>>()

    val message : LiveData<Event<String>>
    get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"

    }

    fun saveOrUpdate(){
        if(isUpdateOrDelete){
            taskToUpdateOrDelete.createdAt = inputCreatedAt.value!!
            taskToUpdateOrDelete.taskName = inputTaskName.value!!
            update(taskToUpdateOrDelete)
        }else {
            val createdAt = inputCreatedAt.value!!
            val taskName = inputTaskName.value!!
            insert(Task(0.createdAt.taskName))
            inputCreatedAt.value = null
            inputTaskName.value = null
        }
    }
    fun clearAllOrDelete(){
        if(isUpdateOrDelete){
            delete(taskToUpdateOrDelete)
        }else{
        clearAll()

    }
    }

    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
        statusMessage.value = Event("Task Inserted Succesfully")
    }

    fun update(task: Task) = viewModelScope.launch {
        repository.update(task)
        inputCreatedAt.value = null
        inputTaskName.value = null
        isUpdateOrDelete = false
        taskToUpdateOrDelete = task
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "ClearAll"
    }

    fun delete(task: Task) = viewModelScope.launch {
        repository.delete(task)
        inputCreatedAt.value = null
        inputTaskName.value = null
        isUpdateOrDelete = false
        taskToUpdateOrDelete = task
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "ClearAll"
        statusMessage.value = Event("Task Deleted Succesfully")
    }

    fun clearAll(task: Task) = viewModelScope.launch {
        repository.deleteAll()
        statusMessage.value = Event("All Tasks Deleted Succesfully")
    }

    fun initUpdateAndDelete(task: Task){
        inputCreatedAt.value = task.createdAt
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