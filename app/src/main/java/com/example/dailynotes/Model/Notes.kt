package com.example.dailynotes.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes_table")

data class Notes(
    @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val title: String,
        val description: String,)
