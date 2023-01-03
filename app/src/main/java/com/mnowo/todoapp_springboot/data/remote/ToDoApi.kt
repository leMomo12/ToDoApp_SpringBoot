package com.mnowo.todoapp_springboot.data.remote

import com.mnowo.todoapp_springboot.domain.models.ToDo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ToDoApi {

    @GET("/todo")
    suspend fun getAllToDoItems() : Collection<ToDo>

    @GET("/todo/{id}")
    suspend fun getToDoItemById(@Path("id") id: Long): ToDo

    @POST("/todo")
    suspend fun addNewToDo(@Body toDo: ToDo)

    @DELETE("/todo/{id}")
    suspend fun deleteToDoItemById(@Path("id") id: Long): Response<Unit>

    @PATCH("/todo")
    suspend fun updateToDo(@Body toDo: ToDo)

}