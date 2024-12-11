package com.example.examenpmdm_ivan.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.examenpmdm_ivan.R
import com.example.examenpmdm_ivan.navigation.Menu

@Composable
fun InicioScreen(modifier: Modifier = Modifier, navController: NavController) {
    Inicio(modifier, navController)
}

@Composable
fun Inicio(modifier: Modifier = Modifier, navController: NavController)  {
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {

        // Box utilizado en blanco para que quede el texto y el icono en el centro de la pantalla
        Box{}

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
            Text("Bienvenido a Cádiz Market", fontSize = 26.sp)

            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo"
            )
        }

        Button(onClick = {navController.navigate(Menu)}, modifier = Modifier.padding(bottom = 30.dp)) {
            Text("Comenzar")
        }

    }
}

