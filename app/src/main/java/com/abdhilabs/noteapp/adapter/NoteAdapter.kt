package com.abdhilabs.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdhilabs.noteapp.data.model.Note
import com.abdhilabs.noteapp.databinding.ItemNoteBinding

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private val listNote = mutableListOf<Note>()

    private var onItemClickListener: ((Note) -> Unit)? = null
    private var onItemDeleteClickListener: ((Note) -> Unit)? = null

    fun fillNote(note: List<Note>) {
        listNote.clear()
        listNote.addAll(note)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.note = note
            binding.root.setOnClickListener { onItemClickListener?.let { it(note) } }
            binding.btnDelete.setOnClickListener { onItemDeleteClickListener?.let { it(note) } }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNote[position])
    }

    override fun getItemCount(): Int = listNote.size

    fun setOnItemClickListener(listener: (Note) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnItemDeleteClickListener(listener: (Note) -> Unit) {
        onItemDeleteClickListener = listener
    }
}