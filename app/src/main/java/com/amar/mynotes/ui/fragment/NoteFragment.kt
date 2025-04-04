package com.amar.mynotes.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.amar.mynotes.R
import com.amar.mynotes.common.showSnackBar
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
     private var initialNote: Note? = null

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
          initialNote = args.note
          setupToolbar()
          setupNoteFields()
     }

     private fun setupNoteFields() {
          initialNote?.let {
               binding.titleEditText.setText(it.title)
               binding.descriptionEditText.setText(it.description)
          }
     }

     private fun setupToolbar() {
          binding.customToolbar.apply {
               toolbarTitle.text = if (initialNote != null) {
                    getString(R.string.update_note)
               } else {
                    getString(R.string.add_note)
               }

               toolbarBackArrow.setOnClickListener {
                    findNavController().navigateUp()
               }

               toolbarSaveIcon.setOnClickListener {
                    hideKeyboard()
                    removeFocus()
                    saveNote()
               }
          }
     }

     private fun saveNote() {
          val title = binding.titleEditText.text.toString().trim()
          val description = binding.descriptionEditText.text.toString().trim()

          if (title.isEmpty() && description.isEmpty()) {
               return
          }

          val noteToSave = initialNote?.copy(
               timestamp = System.currentTimeMillis(),
               title = title,
               description = description,
               currentDateTime = AppUtils.getFormattedDateTime()
          ) ?: Note(
               title = title,
               description = description
          )

          initialNote?.let {
               viewModel.updateNote(noteToSave)
               showSnackBar("Note updated")
          } ?: run {
               viewModel.addNote(noteToSave)
               showSnackBar("Note saved")
          }

          initialNote = noteToSave
     }

     private fun showSnackBar(message: String) {
          binding.root.showSnackBar(message)
     }

     private fun removeFocus() {
          with(binding) {
               if (titleEditText.hasFocus()) {
                    titleEditText.clearFocus()
               }

               if (descriptionEditText.hasFocus()) {
                    descriptionEditText.clearFocus()
               }
          }
     }

     private fun hideKeyboard() {
          val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
          val view = requireActivity().currentFocus ?: View(requireContext())
          inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
     }

     override fun onPause() {
          super.onPause()
          if (hasChanges()) {
               saveNote()
          }
     }

     private fun hasChanges(): Boolean {
          val title = binding.titleEditText.text.toString().trim()
          val description = binding.descriptionEditText.text.toString().trim()
          return (title != initialNote?.title) || (description != initialNote?.description)
     }
}