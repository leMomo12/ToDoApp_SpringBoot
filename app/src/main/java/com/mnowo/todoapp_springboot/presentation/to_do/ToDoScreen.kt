package com.mnowo.todoapp_springboot

import android.util.Log.d
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        ToDoList(viewModel = viewModel, onEditClicked = {
            navController.currentBackStackEntry?.savedStateHandle?.set(
                "toDoId",
                it
            )
            navController.navigate("addToDoScreen")
        })
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
fun ToDoList(viewModel: ToDoViewModel, onEditClicked: (Long) -> Unit) {
    LazyColumn {
        items(viewModel.toDoListData.value.list) { toDo ->
            ToDoListItem(
                toDo = toDo,
                onDeleteClicked = { viewModel.deleteToDoItem(id = it) },
                onEditClicked = { onEditClicked(it) }
            )
        }
    }
}

@Composable
fun ToDoListItem(toDo: ToDo, onDeleteClicked: (Long) -> Unit, onEditClicked: (Long) -> Unit) {
    Row(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(darkerWhite, RoundedCornerShape(8.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ) {
                Text(text = toDo.title)
                Text(text = toDo.description)

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    IconButton(onClick = { onDeleteClicked(toDo.id) }) {
                        Icon(Icons.Default.Delete, contentDescription = "")
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                    IconButton(onClick = { onEditClicked(toDo.id) }) {
                        Icon(Icons.Default.Edit, contentDescription = "")
                    }
                }
            }
        }
    }
}