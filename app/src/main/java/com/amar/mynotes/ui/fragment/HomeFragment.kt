package com.amar.mynotes.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.amar.mynotes.common.hide
import com.amar.mynotes.common.show
import com.amar.mynotes.common.showSnackBar
import com.amar.mynotes.data.database.Note
import com.amar.mynotes.data.repository.NoteRepositoryImpl
import com.amar.mynotes.databinding.FragmentHomeBinding
import com.amar.mynotes.ui.adapter.NoteAdapter
import com.amar.mynotes.ui.adapter.SwipeToDeleteCallback
import com.amar.mynotes.ui.viewmodel.NoteViewModel
import com.amar.mynotes.ui.viewmodel.NoteViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

     private lateinit var binding: FragmentHomeBinding
     private lateinit var allNotes: List<Note>
     private val viewModel: NoteViewModel by viewModels {
          val noteRepository = NoteRepositoryImpl(requireContext().applicationContext)
          NoteViewModelFactory(noteRepository)
     }

     private val noteAdapter by lazy {
          NoteAdapter { note ->
               navigateToNoteFragment(note)
          }
     }

     override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?
     ): View {
          binding = FragmentHomeBinding.inflate(inflater, container, false)
          return binding.root
     }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
          super.onViewCreated(view, savedInstanceState)

          setUpRecyclerView()

          viewModel.allNotes.observe(viewLifecycleOwner) { notes ->
               allNotes = notes
               noteAdapter.submitList(notes)
               scrollToTop()
          }

          binding.searchTextInputLayout.editText?.addTextChangedListener(createSearchTextWatcher())

          binding.floatingActionButton.setOnClickListener {
               navigateToNoteFragment()
          }
     }

     private fun navigateToNoteFragment(note: Note? = null) {
          val action = HomeFragmentDirections.actionHomeFragmentToNoteFragment(note)
          findNavController().navigate(action)
     }

     private fun createSearchTextWatcher() = object : TextWatcher {
          override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
          override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
          override fun afterTextChanged(s: Editable?) = filterNotes(s)
     }

     private fun filterNotes(editable: Editable?) {
          val query = editable.toString().trim()
          if (query.isEmpty()) {
               binding.noItemsFoundText.hide()
               noteAdapter.submitList(allNotes)
          } else {
               val filteredNotes = allNotes.filter { note ->
                    note.title.contains(query, ignoreCase = true) || note.description.contains(query, ignoreCase = true)
               }

               if (filteredNotes.isEmpty()) {
                    binding.noItemsFoundText.show()
               } else {
                    binding.noItemsFoundText.hide()
               }

               noteAdapter.submitList(filteredNotes)
          }
     }

     private fun scrollToTop() {
          lifecycleScope.launch {
               delay(100)
               binding.recyclerView.scrollToPosition(0)
          }
     }

     private fun setUpRecyclerView() {
          binding.recyclerView.apply {
               layoutManager = LinearLayoutManager(requireContext())
               adapter = noteAdapter
          }

          ItemTouchHelper(swipeToDeleteCallback).attachToRecyclerView(binding.recyclerView)
     }

     private val swipeToDeleteCallback = SwipeToDeleteCallback { position ->
          val note = allNotes[position]
          viewModel.deleteNote(note)
          binding.root.showSnackBar(
               message = "Note deleted",
               duration = Snackbar.LENGTH_LONG,
               actionLabel = "UNDO",
               actionCallback = { viewModel.addNote(note) }
          )
     }
}