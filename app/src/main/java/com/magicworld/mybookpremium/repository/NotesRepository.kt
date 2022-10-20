package com.magicworld.mybookpremium.repository

import androidx.lifecycle.LiveData
import com.magicworld.mybookpremium.MyBookPremium
import com.magicworld.mybookpremium.data.NoteDao
import com.magicworld.mybookpremium.model.Note

class NotesRepository {

    private val noteDao: NoteDao = MyBookPremium.database.noteDao()
    val readAllData: LiveData<List<Note>> =noteDao.readAllData()

    suspend fun insertInDatabase(note: Note) {
        noteDao.addNote(note)
    }

    suspend fun updateItem(updateNote: Note) {
        noteDao.updateNote(updateNote)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    suspend fun deleteAllNotes() {
        noteDao.deleteAllNotes()
    }
}