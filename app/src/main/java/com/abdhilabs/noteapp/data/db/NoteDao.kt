package com.abdhilabs.noteapp.data.db

import androidx.room.*
import com.abdhilabs.noteapp.data.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note): Long

    @Update
    fun updateNote(note: Note): Int

    @Query("SELECT * FROM note")
    fun getAllNote(): List<Note>

    @Delete
    fun deleteNote(note: Note)
}