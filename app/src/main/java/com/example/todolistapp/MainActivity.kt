package com.example.todolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todolistapp.Database.TaskDatabase
import com.example.todolistapp.Database.TaskRepository
import com.example.todolistapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao = TaskViewModel.getInstance(application).taskDAO
        val repository = TaskRepository(dao)
        val factory = TaskViewModelFactory(repository)
        taskViewModel = ViewModelProvider(this.factory).get(TaskViewModel::class.java)
        binding.myViewModel = taskViewModel
        binding.lifecycleOwner = this
        displayTasksList()
    }

    private fun initRecyclerView(){
        binding.taskRecyclerView.layoutManager = LinearLayoutManager(this)
        displayTasksList()
    }

    private fun displayTasksList(){
        taskViewModel.tasks.observe(this, Observer {
            Log.i("MYTAG", it.toString())
            binding.taskRecyclerView.adapter = MyRecyclerViewAdapter(it,{selectedTask:Task->listTaskClicked(selectedTask)})
        })
    }

    private fun listTaskClicked(task: Task){
        Toast.makeText(this,"selected createdAt is ${task.createdAt}", Toast.LENGTH_LONG).SHOW()
        taskViewModel.initUpdateAndDelete(task)
    }
}
