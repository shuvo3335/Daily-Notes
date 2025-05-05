package com.example.dailynotes.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dailynotes.Model.Notes

@Dao
interface Note_DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes)

    @Update
    suspend fun updateNotes(notes: Notes)

    @Delete
    suspend fun deleteNotes(notes: Notes)

    @Query("select * from Notes_table order by id asc")
    fun getAllNotes(): LiveData<List<Notes>>

    @Query("select * from Notes_table where id = :id")
    fun getNotesById(id: Int): LiveData<Notes>

    @Query("select * from Notes_table where title like :title")
    fun getNotesByTitle(title: String): LiveData<List<Notes>>

    @Query("select * from Notes_table where description like :description")
    fun getNotesByDescription(description: String): LiveData<List<Notes>>

    @Query("select * from Notes_table where title like :title and description like :description")
    fun getNotesByTitleAndDescription(title: String, description: String): LiveData<List<Notes>>

    @Query("select * from Notes_table where title like :title or description like :description")
    fun getNotesByTitleOrDescription(title: String, description: String): LiveData<List<Notes>>

    @Query("delete from Notes_table")
    suspend fun deleteAllNotes()
}