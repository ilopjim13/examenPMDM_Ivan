package com.example.examenpmdm_ivan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.examenpmdm_ivan.navigation.CadizMarket
import com.example.examenpmdm_ivan.ui.theme.ExamenPMDM_IvanTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamenPMDM_IvanTheme {
                val coroutineScope = rememberCoroutineScope()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
                val selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

                // modalDrawe utilizado para el menu hamburgesa, que se debería abrir al darle clic al icono menu

                ModalNavigationDrawer(
                    drawerContent = {
                        val items = listOf(
                            NavigationItem(
                                title = "Categorías",
                                selectedIcon = Icons.Filled.Star,
                                unselectedIcon = Icons.Filled.Star
                            ),
                            NavigationItem(
                                title = "Tipos de Comercio",
                                selectedIcon = Icons.Filled.Star,
                                unselectedIcon = Icons.Filled.Star
                            ),
                            NavigationItem(
                                title = "Novedades",
                                selectedIcon = Icons.Filled.Star,
                                unselectedIcon = Icons.Filled.Star
                            ),
                            NavigationItem(
                                title = "Rebajas",
                                selectedIcon = Icons.Filled.Star,
                                unselectedIcon = Icons.Filled.Star
                            ),
                        )

                        Column(
                            Modifier
                                .width(360.dp)
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp))
                                .background(Color(0xFFFFFFFF))
                        ) {
                            Spacer(Modifier.height(60.dp))
                            items.forEachIndexed { index, item ->
                                NavigationDrawerItem(
                                    label = { Text(item.title)},
                                    selected = index == selectedItemIndex,
                                    onClick = {
                                        coroutineScope.launch { drawerState.close() }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if(index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                                            contentDescription = item.title
                                        )
                                    },
                                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                )
                            }
                        }

                    }
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        CadizMarket(modifier = Modifier.padding(innerPadding)) { coroutineScope.launch { drawerState.open() } }
                    }
                }
            }
        }
    }
}

// data class para mostrar los items del modalDrawer
data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
)