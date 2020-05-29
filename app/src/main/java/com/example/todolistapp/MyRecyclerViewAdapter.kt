package com.example.todolistapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertController
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.databinding.ListTaskBinding
import kotlinx.android.synthetic.main.list_task.view.*
import java.text.FieldPosition


class MyRecyclerViewAdapter (private val tasksList: List<Task>) : AlertController.RecycleView.Adapter<MyViewHolder>()

    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding : ListTaskBinding = DataBindingUtil.inflate(layoutInflater,R.layout.list_task.parent,false)
            return MyViewHolder(binding)
        }

        override fun getTaskCount(): Int {
            return  tasksList.size
        }

        override fun onBindingViewHolder(holder: MyViewHolder, position: Int){
            holder.bind(tasksList[position])
        }

    }
    class MyViewHolder(val binding: ListTaskBinding) : RecycleView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.createdAtTextView.text = task.createdAt
            binding.taskNameTextView.text = task.createdAt
        }
    }
