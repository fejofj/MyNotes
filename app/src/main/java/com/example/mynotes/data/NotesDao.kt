package com.example.mynotes.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun addNotes(notes: Notes)

    @Query(value = "select * from user_notes order by date DESC")
    fun readNotes():LiveData<List<Notes>>
@Delete
fun delete(notes: Notes)

    @Update
    fun updateNotes(notes: Notes)
}