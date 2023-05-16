package com.example.architecturepatterns.cleanmvvm.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.architecturepatterns.cleanmvvm.data.model.NoteModel
import com.example.architecturepatterns.databinding.ItemNotesBinding

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private val notes: ArrayList<NoteModel> = arrayListOf()
    private lateinit var context: Context

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ItemNotesBinding.inflate(LayoutInflater.from(context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        with(holder.binding) {
            tvTitle.text = notes[position].title
            tvDescription.text = notes[position].description
        }
    }

    override fun getItemCount(): Int = notes.size

    fun setNotes(notes: List<NoteModel>) {
        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }

    class NotesViewHolder(val binding: ItemNotesBinding) : ViewHolder(binding.root)
}