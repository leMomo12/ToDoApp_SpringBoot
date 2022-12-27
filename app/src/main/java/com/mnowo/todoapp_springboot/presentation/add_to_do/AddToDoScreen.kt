package com.mnowo.todoapp_springboot

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mnowo.todoapp_springboot.presentation.add_to_do.AddToDoViewModel

@Composable
fun AddToDoScreen(navController: NavController, viewModel: AddToDoViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
            AddToDoHeader {
                navController.navigate("toDoScreen")
            }
        }
    ) {
        AddToDoBody(viewModel = viewModel) {
            navController.navigate("toDoScreen")
        }
    }
}


@Composable
fun AddToDoHeader(onBackClicked: () -> Unit) {
    TopAppBar {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { onBackClicked() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "")
            }
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Text(text = "Add new toDo")
        }
    }
}

@Composable
fun AddToDoBody(viewModel: AddToDoViewModel, navigateBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.padding(vertical = 50.dp))
        OutlinedTextField(
            value = viewModel.titleState.value,
            onValueChange = {
                viewModel.setTitleState(it)
            },
            label = {
                Text(text = "Enter title")
            }
        )
        Spacer(modifier = Modifier.padding(vertical = 15.dp))
        OutlinedTextField(
            value = viewModel.descriptionState.value,
            onValueChange = {
                viewModel.setDescriptionState(it)
            },
            label = {
                Text(text = "Enter description")
            }
        )
        Spacer(modifier = Modifier.padding(vertical = 30.dp))
        Button(onClick = {
            viewModel.addNewToDo()
            navigateBack()
        }) {
            Text(text = "Add ToDo")
        }
    }
}