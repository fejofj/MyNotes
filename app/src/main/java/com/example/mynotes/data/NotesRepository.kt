package com.example.mynotes.data

import androidx.lifecycle.LiveData

class NotesRepository(private val notesDao: NotesDao) {

    val readAllData:LiveData<List<Notes>> = notesDao.readNotes()

    suspend fun addNotes(notes: Notes){
        notesDao.addNotes(notes)
    }

    fun updateNotes(notes: Notes)
    {
        notesDao.updateNotes(notes)
    }

    fun delete(notes: Notes){
        notesDao.delete(notes)
    }
}