package com.mnowo.todoapp_springboot.presentation.to_do

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnowo.todoapp_springboot.domain.models.ToDo
import com.mnowo.todoapp_springboot.domain.repository.ToDoRepository
import com.mnowo.todoapp_springboot.util.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    private val _toDoListData = mutableStateOf<ListState<ToDo>>(ListState())
    val toDoListData: State<ListState<ToDo>> = _toDoListData

    private fun setToDoListData(value: List<ToDo>) {
        _toDoListData.value = toDoListData.value.copy(
            list = value
        )
    }

    fun getAllToDoItems() = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        val toDoList = repository.getAllToDoItems().toList()

        setToDoListData(toDoList)
    }

    fun deleteToDoItem(id: Long) = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        repository.deleteToDoItemById(id = id)
    }

}