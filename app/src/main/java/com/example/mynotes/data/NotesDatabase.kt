package com.example.mynotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Notes::class],version = 2,exportSchema = false)
abstract class NotesDatabase:RoomDatabase() {

    abstract fun notesDao():NotesDao

    companion object{
        @Volatile
        private var INSTANCE:NotesDatabase?=null
        val migrate_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user_notes ADD COLUMN title TEXT")
            }

        }


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
                ).addMigrations(migrate_1_2)
                    .build()
                INSTANCE=instance
                return instance
            }
        }
    }
}