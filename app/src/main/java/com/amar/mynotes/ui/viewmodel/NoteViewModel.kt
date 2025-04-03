package com.amar.mynotes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amar.mynotes.data.database.Note
import com.amar.mynotes.data.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(
     private val repo: NoteRepository
) : ViewModel() {

     val allNotes: LiveData<List<Note>> = repo.getAllNotes()

     fun addNote(note: Note) {
          viewModelScope.launch {
               repo.add(note)
          }
     }

     fun updateNote(note: Note) {
          viewModelScope.launch {
               repo.update(note)
          }
     }
}