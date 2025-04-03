package com.amar.mynotes.data.repository

import androidx.room.Dao
import com.amar.mynotes.data.database.Note

@Dao
interface NoteRepository {
     suspend fun add(note: Note)
     suspend fun update(note: Note)
     suspend fun delete(note: Note)
     suspend fun getAllNotes(): List<Note>
}