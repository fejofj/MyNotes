package com.example.mynotes.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch



@InternalCoroutinesApi
class NotesViewModel(application: Application):AndroidViewModel(application) {
 val readAllData :LiveData<List<Notes>>
    private val repository:NotesRepository
    init {
        val notesDao = NotesDatabase.getDatabase(application).notesDao()
        repository= NotesRepository(notesDao)
        readAllData = repository.readAllData

    }

 fun addNotes(notes:Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNotes(notes)
        }
    }

    fun updateNotes(notes: Notes){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateNotes(notes)
        }
    }

    fun delete(notes: Notes){
        viewModelScope.launch (Dispatchers.IO){
            repository.delete(notes)
        }
    }
}