package com.magicworld.mybookpremium.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.mybookpremium.model.Note
import com.magicworld.mybookpremium.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {

    private val noteRepository = NotesRepository()
    val readAllData: LiveData<List<Note>> = noteRepository.readAllData

    private val _typeView = MutableLiveData<Boolean>()
    val typeView: LiveData<Boolean> = _typeView

    fun saveTypeView(typeView: Boolean){
        _typeView.value = typeView
    }

    fun deleteAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteAllNotes()
        }
    }
}