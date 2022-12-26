package com.mnowo.todoapp_springboot

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "toDoScreen") {

        composable("toDoScreen") {
            ToDoScreen(navController)
        }

        composable("addToDoScreen") {
            AddToDoScreen(navController)
        }
    }
}