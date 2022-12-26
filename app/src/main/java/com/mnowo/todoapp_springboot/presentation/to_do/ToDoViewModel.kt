package com.mnowo.todoapp_springboot.presentation.to_do

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnowo.todoapp_springboot.domain.models.ToDo
import com.mnowo.todoapp_springboot.domain.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    private val _toDoListData = mutableStateOf<List<ToDo>>(emptyList())
    val toDoListData: State<List<ToDo>> = _toDoListData

    private fun setToDoListData(value: List<ToDo>) {
        _toDoListData.value = value
    }

    fun getAllToDoItems() = viewModelScope.launch(Dispatchers.IO) {
        delay(10000)
        val toDoList = repository.getAllToDoItems().toList()

        setToDoListData(toDoList)
    }
}