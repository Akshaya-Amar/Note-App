package com.amar.mynotes.di

import android.content.Context
import androidx.room.Room
import com.amar.mynotes.data.database.Migrations.MIGRATION_1_2
import com.amar.mynotes.data.database.NoteDao
import com.amar.mynotes.data.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

     @Provides
     @Singleton
     fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
          return Room.databaseBuilder(
               context,
               NoteDatabase::class.java,
               "notes_database"
          )
               .addMigrations(MIGRATION_1_2)
               .build()
     }

     @Provides
     @Singleton
     fun provideNoteDao(database: NoteDatabase): NoteDao = database.noteDao()
}