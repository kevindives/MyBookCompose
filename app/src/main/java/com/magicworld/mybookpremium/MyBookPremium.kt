package com.magicworld.mybookpremium

import android.app.Application
import androidx.room.Room
import com.magicworld.mybookpremium.data.NotesDatabase

class MyBookPremium: Application() {
    companion object{
        lateinit var database : NotesDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            NotesDatabase::class.java,
            "note_db"
        ).build()
    }
}