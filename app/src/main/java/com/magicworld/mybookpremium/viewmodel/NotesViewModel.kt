package com.magicworld.mybookpremium.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.mybookpremium.model.Note
import com.magicworld.mybookpremium.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel : ViewModel() {

    private val noteRepository = NotesRepository()
    val readAllData: LiveData<List<Note>> = noteRepository.readAllData

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _typeView = MutableLiveData<Boolean>()
    val typeView: LiveData<Boolean> = _typeView

    fun saveNote(title: String, description: String) {
        _title.value = title
        _description.value = description
    }

    fun saveTypeView(typeView: Boolean){
        _typeView.value = typeView
    }

    fun insertInDatabase(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insertInDatabase(note)
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteAllNotes()
        }
    }


}

