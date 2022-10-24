package com.magicworld.mybookpremium.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.mybookpremium.model.Note
import com.magicworld.mybookpremium.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel : ViewModel() {

    private val noteRepository = NotesRepository()

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    fun saveNote(title: String, description: String) {
        _title.value = title
        _description.value = description
    }

    fun insertInDatabase(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insertInDatabase(note)
        }
    }

}

