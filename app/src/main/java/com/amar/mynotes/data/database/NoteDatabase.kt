package com.amar.mynotes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

     abstract fun noteDao(): NoteDao

     companion object {
          @Volatile
          private var INSTANCE: NoteDatabase? = null

          fun getInstance(context: Context): NoteDatabase = INSTANCE ?: synchronized(this) {
               val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "notes_database"
               ).build()
               INSTANCE = instance
               instance
          }
     }
}