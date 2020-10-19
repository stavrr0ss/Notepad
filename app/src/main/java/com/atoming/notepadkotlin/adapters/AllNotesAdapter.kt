package com.atoming.notepadkotlin.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.atoming.notepadkotlin.models.DbObject
import com.atoming.notepadkotlin.models.Note
import kotlinx.android.synthetic.main.all_notes_item_links.view.*
import kotlinx.android.synthetic.main.all_notes_item_note.view.*

class AllNotesAdapter {

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val noteTitleTv = itemView.note_title
        private val noteDescriptionTv = itemView.note_description_tv

        fun bind(note: Note) {
            noteTitleTv.text = note.noteTitle
            noteDescriptionTv.text = note.description
        }
    }

    class LinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val linkListTitle = itemView.link_list_title

    }
}