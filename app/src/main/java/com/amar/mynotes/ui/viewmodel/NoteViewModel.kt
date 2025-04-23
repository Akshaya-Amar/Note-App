package com.amar.mynotes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amar.mynotes.data.database.Note
import com.amar.mynotes.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
     private val repo: NoteRepository
) : ViewModel() {
     val allNotes: LiveData<List<Note>> = repo.getAllNotes()
     fun addNote(note: Note) = viewModelScope.launch { repo.add(note) }
     fun updateNote(note: Note) = viewModelScope.launch { repo.update(note) }
     fun deleteNote(note: Note) = viewModelScope.launch { repo.delete(note) }
}