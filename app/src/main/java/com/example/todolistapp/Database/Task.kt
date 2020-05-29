package com.example.todolistapp.Database

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity(tableName = "task_data_table")
data class Task (


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    val id : int,

    @ColumnInfo(name = "task_createdAt")
    val createdAt : string,

    @ColumnInfo(name = "task_taskName")
    val taskName : string
)