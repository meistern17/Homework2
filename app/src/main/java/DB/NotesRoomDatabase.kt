package DB

import Classes.Notes
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Notes::class], version = 1)
abstract class NotesRoomDatabase : RoomDatabase() {

    abstract val notesDao: NotesDao

    companion object {
        fun getDatabase(context: Context): NotesRoomDatabase {
            return Room.databaseBuilder(context, NotesRoomDatabase::class.java, "notes-db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}