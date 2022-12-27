package com.mnowo.todoapp_springboot.data.repository

import android.util.Log.d
import com.mnowo.todoapp_springboot.data.remote.ToDoApi
import com.mnowo.todoapp_springboot.domain.models.ToDo
import com.mnowo.todoapp_springboot.domain.repository.ToDoRepository
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(private val toDoApi: ToDoApi) : ToDoRepository {

    override suspend fun getAllToDoItems(): Collection<ToDo> {
        val a = toDoApi.getAllToDoItems()
        d("ToDoItems", "Hello size: ${a.size}")
        return a
    }

    override suspend fun getToDoItemById(id: Long): ToDo = toDoApi.getToDoItemById(id = id)

    override suspend fun addNewToDo(toDo: ToDo) = toDoApi.addNewToDo(toDo = toDo)
}