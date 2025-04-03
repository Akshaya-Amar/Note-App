package com.amar.mynotes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.amar.mynotes.data.database.Note
import com.amar.mynotes.data.repository.NoteRepositoryImpl
import com.amar.mynotes.databinding.FragmentNoteBinding
import com.amar.mynotes.ui.viewmodel.NoteViewModel
import com.amar.mynotes.ui.viewmodel.NoteViewModelFactory
import com.amar.mynotes.utils.AppUtils

class NoteFragment : Fragment() {

     private lateinit var binding: FragmentNoteBinding
     private val viewModel: NoteViewModel by viewModels {
          val noteRepository = NoteRepositoryImpl(requireContext().applicationContext)
          NoteViewModelFactory(noteRepository)
     }

     private val args: NoteFragmentArgs by navArgs()
     private var note: Note? = null

     override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?
     ): View {
          binding = FragmentNoteBinding.inflate(inflater, container, false)
          return binding.root
     }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
          super.onViewCreated(view, savedInstanceState)

          note = args.note

          // if note is to be updated
          note?.let {
               binding.titleInputLayout.editText?.setText(it.title)
               binding.descriptionInputLayout.editText?.setText(it.description)
               // also set text for update note/add note
               // binding.addEditTextView.setText(getResources().getString(R.string.edit_note));
          }
     }

     override fun onPause() {
          super.onPause()
          saveNote()
     }

     private fun saveNote() {
          val title = binding.titleInputLayout.editText?.text.toString().trim()
          val description = binding.descriptionInputLayout.editText?.text.toString().trim()

          val noteToSave = note?.copy(
               timestamp = System.currentTimeMillis(),
               title = title,
               description = description,
               currentDateTime = AppUtils.getFormattedDateTime()
          ) ?: Note(
               title = title,
               description = description
          )

          if (title.isNotEmpty() || description.isNotEmpty()) {
               note?.let {
                    viewModel.updateNote(noteToSave)
               } ?: viewModel.addNote(noteToSave)
          }
     }
}