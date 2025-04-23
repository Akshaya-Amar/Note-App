package com.amar.mynotes.data.repository

import androidx.lifecycle.LiveData
import com.amar.mynotes.data.database.Note
import com.amar.mynotes.data.database.NoteDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepositoryImpl @Inject constructor(
     private val noteDao: NoteDao
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

     override fun getAllNotes(): LiveData<List<Note>> {
          return noteDao.getNotes()
     }
}