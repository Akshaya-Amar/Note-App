package com.amar.mynotes.di

import com.amar.mynotes.data.repository.NoteRepository
import com.amar.mynotes.data.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

     @Binds
     @Singleton
     abstract fun bindNoteRepository(
          noteRepositoryImpl: NoteRepositoryImpl
     ): NoteRepository
}