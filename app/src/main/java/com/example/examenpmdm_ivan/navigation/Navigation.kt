package com.example.examenpmdm_ivan.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examenpmdm_ivan.screen.InicioScreen
import com.example.examenpmdm_ivan.screen.MenuScreen

@Composable
fun CadizMarket(modifier:Modifier = Modifier, onClickOptions:()->Unit) {
    val navigationController = rememberNavController()

    // navegación, indica que empieza por el Objeto Iniccio y luego dependiendo de cual llames ejecuta una screen
    // u otra
    NavHost(
        navController = navigationController,
        startDestination = Inicio
    ) {
        composable<Inicio> {
            InicioScreen(modifier, navigationController)
        }
        composable<Menu> {
            MenuScreen(modifier, navigationController, onClickOptions)
        }
    }
}