package com.example.dailynotes

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dailynotes.Adapter.NotesAdapter
import com.example.dailynotes.Database.Note_Database
import com.example.dailynotes.Model.Notes
import com.example.dailynotes.Repository.NoteRepository
import com.example.dailynotes.ViewModel.NoteViewModelFactory
import com.example.dailynotes.databinding.ActivityMainBinding
import com.yourpackage.notesapp.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NotesAdapter
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel::class.java)

        // Initialize Adapter
        adapter = NotesAdapter { note ->
            viewModel.removeNote(note)
        }

        // Setup RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Observe LiveData
        observeNotes()

        binding.btnAddNote.setOnClickListener {
            val noteText = binding.etNote.text.toString().trim()
            if (noteText.isNotEmpty()) {
                val note = Notes(title = "Notes Title", description = noteText)
                viewModel.addNote(note)
                binding.etNote.text.clear()
            } else {
                Toast.makeText(this, "Enter a note", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeNotes() {
        viewModel.allNotes.observe(this) { notesList ->
            adapter.submitList(notesList)
        }
    }
}
