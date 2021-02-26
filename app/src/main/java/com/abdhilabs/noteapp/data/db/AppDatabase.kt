package com.abdhilabs.noteapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abdhilabs.noteapp.data.model.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}