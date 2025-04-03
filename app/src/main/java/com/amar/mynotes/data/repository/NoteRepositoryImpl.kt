package com.amar.mynotes.data.repository

import android.content.Context
import com.amar.mynotes.data.database.Note
import com.amar.mynotes.data.database.NoteDao
import com.amar.mynotes.data.database.NoteDatabase

class NoteRepositoryImpl(
     private val context: Context,
     private val database: NoteDatabase = NoteDatabase.getInstance(context),
     private val noteDao: NoteDao = database.noteDao()
) : NoteRepository {
     override suspend fun add(note: Note) {
          noteDao.add(note)
     }

     override suspend fun update(note: Note) {
          noteDao.update(note)
     }

     override suspend fun delete(note: Note) {
          noteDao.delete(note)
     }

     override suspend fun getAllNotes(): List<Note> {
          return noteDao.getNotes()
     }
}