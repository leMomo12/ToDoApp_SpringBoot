package com.mnowo.todoapp_springboot

import android.accounts.AuthenticatorDescription
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mnowo.todoapp_springboot.presentation.to_do.ToDoViewModel

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
                Icons.Default.Add
            }
        }
    }
}

@Composable
fun ToDoList(viewModel: ToDoViewModel) {
    LazyColumn {
        items(viewModel.toDoListData.value) { toDo ->
            ToDoListItem(title = toDo.title, description = toDo.description)
        }
    }
}

@Composable
fun ToDoListItem(title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = title, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            Text(text = description)
        }
    }
}