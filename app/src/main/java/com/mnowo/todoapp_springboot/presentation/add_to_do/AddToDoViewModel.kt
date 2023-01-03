package com.mnowo.todoapp_springboot.presentation.add_to_do

import android.util.Log.d
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnowo.todoapp_springboot.domain.models.ToDo
import com.mnowo.todoapp_springboot.domain.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddToDoViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

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

    private val _editState = mutableStateOf<Boolean>(false)
    val editState: State<Boolean> = _editState

    fun setEditState(value: Boolean) {
        _editState.value = value
    }

    fun addNewToDo() = viewModelScope.launch(Dispatchers.IO) {
        val toDo = ToDo(0, titleState.value, descriptionState.value)

        repository.addNewToDo(toDo)
    }

    fun editingSetup(id: Long) = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        setEditState(true)
        d("ToDoId", "id: $id")
        val toDoItem = repository.getToDoItemById(id)

        setTitleState(toDoItem.title)
        setDescriptionState(toDoItem.description)
    }

    fun updateToDo(id: Long) = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        val toDo = ToDo(id = id, title = titleState.value, description = descriptionState.value)
        repository.updateToDo(toDo)
    }
}