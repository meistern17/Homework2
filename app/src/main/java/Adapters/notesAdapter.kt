package Adapters

import Classes.Notes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import at.fh.swengb.meister.homeexercise2.R
import kotlinx.android.synthetic.main.item_notes.view.*


class NotesAdapter(): RecyclerView.Adapter<NotesViewHolder>() {
    var notesList = listOf<Notes>()

    fun updateList(newList: List<Notes>) {
        notesList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): NotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val notesItemView = inflater.inflate(R.layout.item_notes, parent, false)
        return NotesViewHolder(notesItemView)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(viewHolder: NotesViewHolder, position: Int) {
        val notes = notesList[position]
        viewHolder.bindItem(notes) // Unresolved reference, i.e. the function bindItem does not exist. Why?
    }
}

class NotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bindItem(notes: Notes) {
        itemView.note_title.text = notes.title
        itemView.note_content.text = notes.content
    }

}