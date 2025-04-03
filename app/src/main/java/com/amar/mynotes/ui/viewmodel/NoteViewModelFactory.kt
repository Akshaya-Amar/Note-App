package com.amar.mynotes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amar.mynotes.data.repository.NoteRepository

class NoteViewModelFactory(
     private val repo: NoteRepository
) : ViewModelProvider.Factory {
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
          if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
               return NoteViewModel(repo) as T
          }
          return super.create(modelClass)
     }
}