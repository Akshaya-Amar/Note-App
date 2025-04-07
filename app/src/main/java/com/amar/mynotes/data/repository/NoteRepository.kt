package com.amar.mynotes.data.repository

import androidx.lifecycle.LiveData
import com.amar.mynotes.data.database.Note

interface NoteRepository {
     suspend fun add(note: Note)
     suspend fun update(note: Note)
     suspend fun delete(note: Note)
     fun getAllNotes(): LiveData<List<Note>>
}