package at.fh.swengb.meister.homeexercise2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pref = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        edit_name.setText(pref.getString("Name", "Name"))
        edit_age.setText(pref.getInt("Age", 0).toString())
    }

    fun onKlickSave(view: View) {
        val ageString =  edit_age.text.toString()
        var age : Int = -1
        val name = edit_name.text.toString()

        if(ageString!="")
            age = ageString.toInt()

        if(age >= 0 && name!="") {
            pref.edit().putInt("Age",age).apply()
            pref.edit().putString("Name", name).apply()
            val intent = Intent(this, NoteListActivity::class.java)
            startActivity(intent)
        }
    }
}
