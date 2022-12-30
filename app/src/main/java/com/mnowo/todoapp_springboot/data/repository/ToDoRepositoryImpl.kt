package com.mnowo.todoapp_springboot.data.repository

import android.util.Log.d
import com.mnowo.todoapp_springboot.data.remote.ToDoApi
import com.mnowo.todoapp_springboot.domain.models.ToDo
import com.mnowo.todoapp_springboot.domain.repository.ToDoRepository
import okhttp3.Response
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(private val toDoApi: ToDoApi) : ToDoRepository {

    override suspend fun getAllToDoItems(): Collection<ToDo> = toDoApi.getAllToDoItems()

    override suspend fun getToDoItemById(id: Long): ToDo = toDoApi.getToDoItemById(id = id)

    override suspend fun addNewToDo(toDo: ToDo) = toDoApi.addNewToDo(toDo = toDo)

    override suspend fun deleteToDoItemById(id: Long) = toDoApi.deleteToDoItemById(id)

    override suspend fun updateToDo(toDo: ToDo) = toDoApi.updateToDo(toDo)
}