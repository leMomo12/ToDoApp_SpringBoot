package com.mnowo.todoapp_springboot.data.remote

import com.mnowo.todoapp_springboot.domain.models.ToDo
import retrofit2.http.GET
import retrofit2.http.POST

interface ToDoApi {

    @GET("/todo")
    suspend fun getAllToDoItems() : Collection<ToDo>

    @GET("/todo/{id}")
    suspend fun getToDoItemById(id: Long): ToDo

    @POST("/todo")
    suspend fun addNewToDo(toDo: ToDo)
}