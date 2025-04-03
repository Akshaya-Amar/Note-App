package com.amar.mynotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
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
               binding.titleTextView.text = note.title
               binding.descriptionTextView.text = note.description
               binding.root.setOnClickListener {
                    onItemClick(note)
               }
          }
     }
}