package com.abdhilabs.noteapp.data.db

import android.content.Context
import androidx.room.Room

object RoomDb {

    fun provideDb(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "note.db").build()
    }
}