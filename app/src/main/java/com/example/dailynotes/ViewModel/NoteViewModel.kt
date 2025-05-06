package com.yourpackage.notesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailynotes.Model.Notes
import com.example.dailynotes.Repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    val allNotes: LiveData<List<Notes>> = repository.getAllNotes()

    fun addNote(note: Notes) {
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }

    fun removeNote(note: Notes) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }
}

