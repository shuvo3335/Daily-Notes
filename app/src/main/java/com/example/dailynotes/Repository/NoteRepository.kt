package com.example.dailynotes.Repository

import androidx.lifecycle.LiveData
import com.example.dailynotes.Database.Note_DAO
import com.example.dailynotes.Model.Notes
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: Note_DAO) {

    suspend fun insertNote(notes: Notes) {
        noteDao.insertNotes(notes)
    }

    suspend fun deleteNote(notes: Notes) {
        noteDao.deleteNotes(notes)
    }

    fun getAllNotes(): LiveData<List<Notes>> {
        return noteDao.getAllNotes()
    }
}