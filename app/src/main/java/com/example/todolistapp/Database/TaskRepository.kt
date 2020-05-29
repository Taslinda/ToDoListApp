package com.example.todolistapp.Database

class TaskRepository(private  val dao : TaskDAO) {

    val tasks = dao.getAllTasks()

    suspend fun insert(task: Task){
        dao.insertTask(task)
    }

    suspend fun update(task: Task){
        dao.updateTask(task)
    }

    suspend fun delete(task: Task){
        dao.deleteTask(task)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }
}