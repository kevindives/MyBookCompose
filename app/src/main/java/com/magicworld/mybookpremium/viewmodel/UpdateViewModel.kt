package com.magicworld.mybookpremium.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.mybookpremium.model.Note
import com.magicworld.mybookpremium.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateViewModel: ViewModel() {

    private val noteRepository = NotesRepository()

    private val _titleUpdate = MutableLiveData<String>()
    val titleUpdate: LiveData<String> = _titleUpdate

    private val _descriptionUpdate = MutableLiveData<String>()
    val descriptionUpdate: LiveData<String> = _descriptionUpdate

    private val _changeColor = MutableLiveData<Long>()
    val changedColor: LiveData<Long> = _changeColor

    fun updateNote(updateNote: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.updateNote(updateNote)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteNote(note)
        }
    }

    fun saveNoteUpdate(titleUpdate: String, descriptionUpdate: String) {
        _titleUpdate.value = titleUpdate
        _descriptionUpdate.value = descriptionUpdate
    }

    fun saveColor(color: Long) {
        _changeColor.value = color
    }


}