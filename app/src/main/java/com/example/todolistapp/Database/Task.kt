package com.example.todolistapp.Database

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity(tableName = "task_data_table")
data class Task (


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    var id : int,

    @ColumnInfo(name = "task_createdAt")
    var createdAt : string,

    @ColumnInfo(name = "task_taskName")
    var taskName : string
)