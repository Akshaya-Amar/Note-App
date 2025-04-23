package com.amar.mynotes.di

import android.content.Context
import com.amar.mynotes.data.database.NoteDao
import com.amar.mynotes.data.database.NoteDatabase
import com.amar.mynotes.data.repository.NoteRepository
import com.amar.mynotes.data.repository.NoteRepositoryImpl
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
     fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase = NoteDatabase.getInstance(context)

     @Provides
     @Singleton
     fun provideNoteDao(database: NoteDatabase): NoteDao = database.noteDao()

     @Provides
     @Singleton
     fun provideNoteRepository(noteDao: NoteDao): NoteRepository = NoteRepositoryImpl(noteDao)

}