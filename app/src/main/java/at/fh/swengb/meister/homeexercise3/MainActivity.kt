package at.fh.swengb.meister.homeexercise2

import Classes.User
import DB.NotesRoomDatabase
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var pref: SharedPreferences
    lateinit var db: NotesRoomDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pref = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        db = NotesRoomDatabase.getDatabase(this)

        //edit_name.setText(pref.getString("Name", "Name"))
        //edit_age.setText(pref.getInt("Age", 0).toString())

        val userId = (pref.getLong("UserId", -1))
        val user = db.notesDao.selectUser(Id = userId)

        if (user != null) {
            val intent = Intent(this, NoteListActivity::class.java)
            startActivity(intent)
            return
        }
    }

    fun onKlickSave(view: View) {
        val ageString = edit_age.text.toString()
        var age: Int = -1
        val name = edit_name.text.toString()

        if (ageString != "")
            age = ageString.toInt()

        if (age >= 0 && name != "") {
            val exUser = db.notesDao.selectUser(name)
            if (exUser == null) {
                val newUser = User(name, age)
                db.notesDao.insert(newUser)
                val newUserWithId = db.notesDao.selectUser(name)
                if (newUserWithId != null) {
                    pref.edit().putLong("UserId", newUserWithId.Id).apply()
                }
                else
                {
                    Toast.makeText(this,"Error on insert",Toast.LENGTH_LONG).show()
                    return
                }

            } else {
                pref.edit().putLong("UserId", exUser.Id).apply()
            }


            val intent = Intent(this, NoteListActivity::class.java)
            startActivity(intent)
        }
    }
}
