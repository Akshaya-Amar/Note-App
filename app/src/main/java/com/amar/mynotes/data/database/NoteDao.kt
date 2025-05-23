package com.amar.mynotes.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
     @Insert
     suspend fun add(note: Note)

     @Update
     suspend fun update(note: Note)

     @Delete
     suspend fun delete(note: Note)

     @Query("SELECT * FROM notes ORDER BY timestamp DESC")
     fun getNotes(): LiveData<List<Note>>
}