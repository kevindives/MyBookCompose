package com.magicworld.mybookpremium.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.magicworld.mybookpremium.model.Note

@Database(entities = [Note::class], version = 1 , exportSchema = false )
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao

}