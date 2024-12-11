package com.example.examenpmdm_ivan.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.examenpmdm_ivan.model.Producto

@Composable
fun MenuScreen(modifier: Modifier = Modifier,navController: NavController, onClickOptions:()-> Unit) {
    Menu(modifier, navController, onClickOptions)
}

@Composable
fun Menu(modifier: Modifier = Modifier,navController: NavController, onClickOptions:()-> Unit) {

    val lisProductos = listOf(
        Producto("Turron", 2, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"),
        Producto("Turron", 2, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"),
        Producto("Turron", 2, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"),
        Producto("Turron", 2, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"),
        Producto("Turron", 2, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"),
        Producto("Turron", 2, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"),
        Producto("Turron", 2, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"),
        Producto("Turron", 2, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"),
        Producto("Turron", 2, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"),
        Producto("Turron", 2, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"),
        Producto("Turron", 2, "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum")
    )
    var producto by remember { mutableStateOf(Producto("", 0, "")) }
    var showDialog by remember { mutableStateOf(false) }

    // Si se le da click a un producto salta la ventana emergente

    if (showDialog) {
        MasInfor(producto) { showDialog = false }
    }


    Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally) {
        Cabecera(onClickOptions, navController)

        Spacer(Modifier.height(30.dp))

        // dentro de otro column para darle a los border un padding y que no quede todo del mismo tamaño que la pantalla
        Column(
            Modifier
                .padding(end = 40.dp, start = 40.dp)
                .fillMaxWidth()
        ) {

            Botones()

            Spacer(Modifier.height(10.dp))

            Text("Productos", fontSize = 24.sp)

            Spacer(Modifier.height(10.dp))

            LazyColumn {
                items(lisProductos.size) { p ->
                    Productos(lisProductos[p],
                        onClick = {
                        showDialog = true
                        producto = lisProductos[p]
                    })
                }
            }

        }
    }
}

@Composable
fun Botones() {
    Row(Modifier.fillMaxWidth()) {
        Button(onClick = {}, Modifier.weight(1f)) {
            Text("Categorias")
        }

        Button(onClick = {}, Modifier.weight(1f)) {
            Text("Comercios")
        }
    }
    Row(Modifier.fillMaxWidth()) {
        Button(onClick = {}, Modifier.weight(1f)) {
            Text("Novedades")
        }

        Button(onClick = {}, Modifier.weight(1f)) {
            Text("Rebajas")
        }
    }
}

@Composable
fun Productos(producto: Producto, onClick: ()-> Unit) {

    // muestra los productos en orden

    Row(
        Modifier
            .fillMaxWidth()
            .clickable(onClick =  onClick )
            , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.AccountBox,
                contentDescription = "Icono",
                modifier = Modifier.size(60.dp)
            )
            Spacer(Modifier.height(5.dp))
            Text(producto.nombre)
        }
        Text("Precio: ${producto.precio}€")

    }
}

@Composable
fun MasInfor(producto: Producto, onShowDialog:()->Unit) {

    // ventana emergente que saltará al darle click a un producto
    Dialog(onDismissRequest = onShowDialog ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountBox,
                    contentDescription = "Icono",
                    Modifier.size(60.dp)
                )
                Spacer(Modifier.height(15.dp))
                Text("Nombre: ${producto.nombre}")
                Text("Descripcion: ${producto.desc}", textAlign = TextAlign.Center)
                Text("Precio: ${producto.precio}€")
            }
        }
    }
}


/**
 * Cabecera utilizada para el menú deberia abrir un menu hamburgesa al darle al icono Menu pero no lo hace
 */
@Composable
fun Cabecera( onClickOptions:()-> Unit, navController: NavController) {
    Row(Modifier.fillMaxWidth().background(Color(0xFF005ABC)), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onClickOptions) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu",
                tint = Color.White
            )
        }

        Text("Cádiz Market", fontSize = 30.sp, color = Color.White)

        IconButton(onClick = {navController.popBackStack()}) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Opciones",
                tint = Color.White
            )
        }
    }
}