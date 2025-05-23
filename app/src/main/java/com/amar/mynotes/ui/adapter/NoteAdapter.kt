package com.amar.mynotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amar.mynotes.common.show
import com.amar.mynotes.data.database.Note
import com.amar.mynotes.databinding.NoteItemBinding

class NoteAdapter(
     private val onItemClick: (Note) -> Unit
) : ListAdapter<Note, NoteAdapter.NoteViewHolder>(DiffCallBack()) {

     class DiffCallBack : DiffUtil.ItemCallback<Note>() {
          override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
               return oldItem.id == newItem.id
          }

          override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
               return oldItem == newItem
          }
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
          val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
          return NoteViewHolder(binding)
     }

     override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
          val note = getItem(position)
          holder.bind(note, onItemClick)
     }

     class NoteViewHolder(
          private val binding: NoteItemBinding
     ) : RecyclerView.ViewHolder(binding.root) {
          fun bind(note: Note, onItemClick: (Note) -> Unit) {
               with(binding) {
                    if (note.title.isNotEmpty()) {
                         titleTextView.show()
                         titleTextView.text = note.title
                    }

                    if (note.description.isNotEmpty()) {
                         descriptionTextView.show()
                         descriptionTextView.text = note.description
                    }

                    dateTimeTextView.text = note.currentDateTime

                    binding.root.setOnClickListener {
                         onItemClick(note)
                    }
               }
          }
     }
}