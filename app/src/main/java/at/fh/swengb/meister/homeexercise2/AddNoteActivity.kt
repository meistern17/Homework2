package at.fh.swengb.meister.homeexercise2

import Classes.Notes
import DB.NotesRoomDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.add_note.*

class AddNoteActivity : AppCompatActivity() {

    lateinit var db: NotesRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_note)

        db = NotesRoomDatabase.getDatabase(this)
    }

    fun addNotes(view: View) {
        val title = edit_title.text.toString()
        val content = edit_content.text.toString()

        val notes = Notes(title, content)

        db.notesDao.insert(notes)

        finish()
    }
}

