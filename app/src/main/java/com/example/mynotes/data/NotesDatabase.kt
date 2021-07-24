package com.example.mynotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Notes::class],version = 1,exportSchema = false)
abstract class NotesDatabase:RoomDatabase() {

    abstract fun notesDao():NotesDao

    companion object{
        @Volatile
        private var INSTANCE:NotesDatabase?=null


        @InternalCoroutinesApi
        fun getDatabase(context: Context):NotesDatabase{
            val temp = INSTANCE
            if (temp!=null){
                return temp
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes_database"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}