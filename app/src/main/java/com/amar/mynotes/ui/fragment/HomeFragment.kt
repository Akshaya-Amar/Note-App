package com.amar.mynotes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.amar.mynotes.data.database.Note
import com.amar.mynotes.data.repository.NoteRepositoryImpl
import com.amar.mynotes.databinding.FragmentHomeBinding
import com.amar.mynotes.ui.adapter.NoteAdapter
import com.amar.mynotes.ui.viewmodel.NoteViewModel
import com.amar.mynotes.ui.viewmodel.NoteViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

     private lateinit var binding: FragmentHomeBinding
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

          viewModel.getNotes()
          viewModel.allNotes.observe(viewLifecycleOwner) {
               noteAdapter.submitList(it)
               lifecycleScope.launch {
                    delay(100)
                    binding.recyclerView.scrollToPosition(0)
               }
          }

          binding.floatingActionButton.setOnClickListener {
               navigateToNoteFragment()
          }
     }

     private fun navigateToNoteFragment(note: Note? = null) {
          val action = HomeFragmentDirections.actionHomeFragmentToNoteFragment(note)
          findNavController().navigate(action)
     }

     private fun setUpRecyclerView() {
          binding.recyclerView.apply {
               layoutManager = LinearLayoutManager(requireContext())
               adapter = noteAdapter
          }
     }
}