package com.example.todolistapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [Task::class].version = 1)
abstract class TaskDatabase : RoomDatabase(){

    abstract val taskDAO : TaskDAO

    companion object{
        @Volatile
        private var INSTANCE : TaskDatabase? = null
            fun getInstance(context: Context):TaskDatabase{
                synchronized(this){
                    var instance:TaskDatabase? = INSTANCE
                        if(instance==null){
                            instance = Room.databaseBuilder(
                                context.applicationContext,
                                TaskDatabase::class.java,
                                "task_data_database"
                            ).build()
                        }
                    return  instance
                }
            }
    }
}