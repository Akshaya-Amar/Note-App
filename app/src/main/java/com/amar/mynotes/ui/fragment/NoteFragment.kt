package com.amar.mynotes.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
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
     private val note: Note? by lazy {
          args.note
     }

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
          setupToolbar()
          setupNoteFields()
     }

     private fun setupNoteFields() {
          note?.let {
               binding.titleEditText.setText(it.title)
               binding.descriptionEditText.setText(it.description)
          }
     }

     private fun setupToolbar() {
          binding.customToolbar.apply {
               toolbarTitle.text = if (note != null) {
                    getString(R.string.update_note)
               } else {
                    getString(R.string.add_note)
               }

               toolbarBackArrow.setOnClickListener {
                    saveNote()
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

          val oldTitle = note?.title
          val oldDescription = note?.description

          if (oldTitle?.equals(title) == true && oldDescription?.equals(description) == true) {
               Log.d("check....if inside", "saveNote: ")
               return
          }
          Log.d("check....if outside", "saveNote: ")

          if (title.isEmpty() && description.isEmpty()) {
               return
          }

          val noteToSave = note?.copy(
               timestamp = System.currentTimeMillis(),
               title = title,
               description = description,
               currentDateTime = AppUtils.getFormattedDateTime()
          ) ?: Note(
               title = title,
               description = description
          )

          note?.let {
               viewModel.updateNote(noteToSave)
               showSnackBar("Note updated")
          } ?: run {
               viewModel.addNote(noteToSave)
               showSnackBar("Note saved")
          }
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
          val inputMethodManager =
               requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
          val view = requireActivity().currentFocus ?: View(requireContext())
          inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
     }

     override fun onPause() {
          super.onPause()
          saveNote()
     }
}