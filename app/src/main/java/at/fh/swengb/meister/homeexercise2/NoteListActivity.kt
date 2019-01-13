package at.fh.swengb.meister.homeexercise2

import Adapters.NotesAdapter
import Classes.Notes
import DB.NotesRoomDatabase
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    private var notes = mutableListOf<Notes>()

    private val notesAdapter = NotesAdapter()

    lateinit var pref: SharedPreferences
    lateinit var db: NotesRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        recycler_view.adapter = notesAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        pref = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        user_info.text = "Notes for "+pref.getString("Name","Name")+", "+  pref.getInt("Age",0)

        db = NotesRoomDatabase.getDatabase(this)
    }

    fun openAddNoteActivity(view : View)
    {
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        notes = db.notesDao.select().toMutableList()
        notesAdapter.updateList(notes)
    }
}
