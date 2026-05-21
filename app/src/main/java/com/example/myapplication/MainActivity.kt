package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // NEW CONCEPT: Parahyangan Heritage (Green, Wood, Earthy)
        val parahyanganScheme = lightColorScheme(
            primary = Color(0xFF4CAF50), // Leaf Green
            onPrimary = Color.White,
            primaryContainer = Color(0xFFC8E6C9), // Soft Green
            onPrimaryContainer = Color(0xFF1B5E20), // Dark Green
            secondary = Color(0xFF795548), // Wood Brown
            onSecondary = Color.White,
            background = Color(0xFFFFFDE7), // Creamy Rice Paper
            surface = Color.White,
            onSurface = Color(0xFF3E2723), // Dark Brown
            surfaceVariant = Color(0xFFF1F8E9) // Very light green
        )

        setContent {
            MaterialTheme(colorScheme = parahyanganScheme) {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                Scaffold(
                    bottomBar = {
                        if (currentDestination?.route in listOf("home", "menu", "profile")) {
                            NavigationBar(
                                containerColor = Color.White,
                                tonalElevation = 8.dp
                            ) {
                                NavigationBarItem(
                                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                                    label = { Text("Home") },
                                    selected = currentDestination?.hierarchy?.any { it.route == "home" } == true,
                                    onClick = {
                                        navController.navigate("home") {
                                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                                NavigationBarItem(
                                    icon = { Icon(Icons.Default.Menu, contentDescription = null) },
                                    label = { Text("Menu") },
                                    selected = currentDestination?.hierarchy?.any { it.route == "menu" } == true,
                                    onClick = {
                                        navController.navigate("menu") {
                                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                                NavigationBarItem(
                                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                                    label = { Text("Profile") },
                                    selected = currentDestination?.hierarchy?.any { it.route == "profile" } == true,
                                    onClick = {
                                        navController.navigate("profile") {
                                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .background(MaterialTheme.colorScheme.background)
                            .bambooTexture(),
                        color = Color.Transparent
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = "home"
                        ) {
                            composable("home") { HomeScreen(navController) }
                            composable("menu") { MenuScreen(navController) }
                            composable(
                                "detail/{menuId}",
                                arguments = listOf(navArgument("menuId") { type = androidx.navigation.NavType.StringType })
                            ) { backStackEntry ->
                                val menuId = backStackEntry.arguments?.getString("menuId") ?: ""
                                DetailMenuScreen(navController, menuId)
                            }
                            composable("profile") { ProfileScreen(navController) }
                            composable("edit_profile") { EditProfileScreen(navController) }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Adds a natural bamboo-like weave texture
 */
fun Modifier.bambooTexture(): Modifier = this.drawBehind {
    val lineAlpha = 0.05f
    val spacing = 16.dp.toPx()
    
    // Horizontal lines
    var y = 0f
    while (y < size.height) {
        drawLine(
            color = Color(0xFF795548).copy(alpha = lineAlpha),
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = 1.dp.toPx()
        )
        y += spacing
    }
    
    // Vertical lines
    var x = 0f
    while (x < size.width) {
        drawLine(
            color = Color(0xFF795548).copy(alpha = lineAlpha),
            start = Offset(x, 0f),
            end = Offset(x, size.height),
            strokeWidth = 1.dp.toPx()
        )
        x += spacing
    }
}
