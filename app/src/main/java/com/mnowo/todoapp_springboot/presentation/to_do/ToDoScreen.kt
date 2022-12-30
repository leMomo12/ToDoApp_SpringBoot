package com.mnowo.todoapp_springboot

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mnowo.todoapp_springboot.domain.models.ToDo
import com.mnowo.todoapp_springboot.presentation.to_do.ToDoViewModel
import com.mnowo.todoapp_springboot.ui.theme.darkerWhite

@Composable
fun ToDoScreen(navController: NavController, viewModel: ToDoViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = true) {
        viewModel.getAllToDoItems()
    }

    Scaffold(
        topBar = {
            Header {
                navController.navigate("addToDoScreen")
            }
        }
    ) {
        ToDoList(viewModel = viewModel)
    }
}

@Composable
fun Header(onAddClick: () -> Unit) {
    TopAppBar {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "ToDo", fontWeight = FontWeight.Black)
            IconButton(onClick = { onAddClick() }) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
        }
    }
}

@Composable
fun ToDoList(viewModel: ToDoViewModel) {
    LazyColumn {
        items(viewModel.toDoListData.value.list) { toDo ->
            ToDoListItem(toDo = toDo, onDeleteClicked = { viewModel.deleteToDoItem(id = it) })
        }
    }
}

@Composable
fun ToDoListItem(toDo: ToDo, onDeleteClicked: (Long) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = darkerWhite
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = toDo.title, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.padding(vertical = 5.dp))
                Text(text = toDo.description)
            }
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.End) {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Edit, contentDescription = "")
                }
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                IconButton(onClick = { onDeleteClicked(toDo.id) }) {
                    Icon(Icons.Default.Delete, contentDescription = "")
                }
            }
        }
    }
}