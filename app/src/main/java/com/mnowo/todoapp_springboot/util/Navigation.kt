package com.mnowo.todoapp_springboot

import android.util.Log.d
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
            val toDoId = navController.previousBackStackEntry?.savedStateHandle?.get<Long>("toDoId")
            d("ToDoId", "here id: $toDoId")
            AddToDoScreen(navController, toDoId = toDoId)
        }
    }
}