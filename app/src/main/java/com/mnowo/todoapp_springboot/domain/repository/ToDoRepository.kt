package com.mnowo.todoapp_springboot.domain.repository

import com.mnowo.todoapp_springboot.domain.models.ToDo

interface ToDoRepository {

    suspend fun getAllToDoItems() : Collection<ToDo>

    suspend fun getToDoItemById(id: Long): ToDo

    suspend fun addNewToDo(toDo: ToDo)
}