package com.jtor.odetocarstar.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jtor.odetocarstar.presentation.util.route.Screen
import com.jtor.odetocarstar.presentation.makes.CarMakeScreen
import com.jtor.odetocarstar.presentation.util.theme.OdeToCarStarTheme
import com.jtor.odetocarstar.presentation.year.CarYearScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OdeToCarStarTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CarMakeScreen.route
                    ) {
                        composable(route = Screen.CarMakeScreen.route) {
                            CarMakeScreen(navController)
                        }

                        composable(route = Screen.CarYearScreen.route + "/{makeId}",
                            arguments = listOf(
                                navArgument("makeId") {
                                    type = NavType.StringType
                                    defaultValue = ""
                                }
                            )
                        ) { entry ->
                            CarYearScreen(make = entry.arguments?.getString("makeId")!!)
                        }
                    }
                }
            }
        }
    }
}