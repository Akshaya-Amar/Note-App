package com.amar.mynotes.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amar.mynotes.data.database.Note
import com.amar.mynotes.data.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(
     private val repo: NoteRepository
) : ViewModel() {

     private var _allNotes = MutableLiveData<List<Note>>()
     val allNotes: LiveData<List<Note>> get() = _allNotes

     /*init {
          getNotes()
     }*/

     fun getNotes() {
          viewModelScope.launch {
               val notes = repo.getAllNotes()
               Log.d("check...", "getNotes")
               _allNotes.postValue(notes)
          }
     }

     fun addNote(note: Note) {
          viewModelScope.launch {
               repo.add(note)
          }
     }

     fun updateNote(note: Note) {
          viewModelScope.launch {
               repo.update(note)
               Log.d("check...", "updateNote: ")
          }
     }
}