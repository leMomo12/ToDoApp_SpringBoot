package com.mnowo.todoapp_springboot.domain.repository

import com.mnowo.todoapp_springboot.domain.models.ToDo
import okhttp3.Response
import retrofit2.http.Body

interface ToDoRepository {

    suspend fun getAllToDoItems() : Collection<ToDo>

    suspend fun getToDoItemById(id: Long): ToDo

    suspend fun addNewToDo(toDo: ToDo)

    suspend fun deleteToDoItemById(id: Long)

    suspend fun updateToDo(@Body toDo: ToDo)
}