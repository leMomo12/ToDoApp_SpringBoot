package com.mnowo.todoapp_springboot.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.mnowo.todoapp_springboot.Navigation
import com.mnowo.todoapp_springboot.ui.theme.ToDoAppSpringBootTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppSpringBootTheme {
                val navController = rememberNavController()
                Navigation(navController = navController)
            }
        }
    }
}