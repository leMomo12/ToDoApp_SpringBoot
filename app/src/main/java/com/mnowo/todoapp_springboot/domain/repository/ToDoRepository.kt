package com.mnowo.todoapp_springboot.domain.repository

import com.mnowo.todoapp_springboot.domain.models.ToDo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

interface ToDoRepository {

    suspend fun getAllToDoItems() : Collection<ToDo>

    suspend fun getToDoItemById(id: Long): ToDo

    suspend fun addNewToDo(toDo: ToDo)

    suspend fun deleteToDoItemById(id: Long): Response<Unit>

    suspend fun updateToDo(toDo: ToDo)
}