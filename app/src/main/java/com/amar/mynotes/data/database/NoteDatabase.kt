package com.amar.mynotes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Note::class], version = 2)
abstract class NoteDatabase : RoomDatabase() {

     abstract fun noteDao(): NoteDao

     companion object {
          @Volatile
          private var INSTANCE: NoteDatabase? = null

          private val MIGRATION_1_2 = object : Migration(1, 2) {
               override fun migrate(db: SupportSQLiteDatabase) {
                    db.execSQL("ALTER TABLE notes ADD COLUMN timestamp INTEGER NOT NULL DEFAULT 0")
               }
          }

          fun getInstance(context: Context): NoteDatabase = INSTANCE ?: synchronized(this) {
               val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "notes_database"
               )
                    .addMigrations(MIGRATION_1_2)
                    .build()
               INSTANCE = instance
               instance
          }
     }
}