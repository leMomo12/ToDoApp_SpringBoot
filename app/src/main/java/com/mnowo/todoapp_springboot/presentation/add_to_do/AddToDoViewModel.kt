package com.mnowo.todoapp_springboot.presentation.add_to_do

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnowo.todoapp_springboot.domain.models.ToDo
import com.mnowo.todoapp_springboot.domain.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddToDoViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    private val _titleState = mutableStateOf<String>("")
    val titleState: State<String> = _titleState

    fun setTitleState(value: String) {
        _titleState.value = value
    }

    private val _descriptionState = mutableStateOf<String>("")
    val descriptionState: State<String> = _descriptionState

    fun setDescriptionState(value: String) {
        _descriptionState.value = value
    }

    fun addNewToDo() = viewModelScope.launch(Dispatchers.IO) {
        val toDo = ToDo(0, titleState.value, descriptionState.value)

        repository.addNewToDo(toDo)
    }
}