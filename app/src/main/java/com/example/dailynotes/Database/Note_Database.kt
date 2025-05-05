package com.example.dailynotes.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dailynotes.Model.Notes

@Database (entities = [Notes::class], version = 1, exportSchema = false)

abstract class Note_Database : RoomDatabase() {

    abstract fun getNoteDao() : Note_DAO

    companion object {
        @Volatile
        private var INSTANCE: Note_Database? = null

        fun getDatabase(context: Context): Note_Database {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Note_Database::class.java,
                    "note_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }

}