package com.jtor.odetocarstar.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.jtor.odetocarstar.presentation.makes.CarMakeScreen
import com.jtor.odetocarstar.presentation.models.CarModelScreen
import com.jtor.odetocarstar.presentation.trims.CarTrimScreen
import com.jtor.odetocarstar.presentation.util.route.Screen
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
                    NavHost(navController = navController, startDestination = Screen.CarMakeScreen.route) {
                        myNavGraph(navController)
                    }
                }
            }
        }
    }

    fun NavGraphBuilder.myNavGraph(navController: NavController) {
        navigation(
            startDestination = "CarMakeScreen", route = Screen.CarMakeScreen.route
        ) {
            composable(
                "CarMakeScreen",
                enterTransition = {
                    return@composable fadeIn(tween(1000))
                },
                exitTransition = {
                    return@composable slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                    )
                },
                popEnterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                    )
                }
            ) {
                CarMakeScreen(navController)
            }

            composable(
                Screen.CarYearScreen.route + "/{makeId}",
                enterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                    )
                },
                popExitTransition = {
                    return@composable slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                    )
                },
            ) { entry ->
                CarYearScreen(
                    make = entry.arguments?.getString("makeId")!!,
                    navController = navController
                )
            }

            composable(
                route = Screen.CarModelScreen.route + "/{make}/{year}",
                enterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                    )
                },
                popExitTransition = {
                    return@composable slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                    )
                },
                arguments = listOf(
                    navArgument("make") {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("year") {
                        type = NavType.StringType
                        defaultValue = "2020"
                    }
                )
            ) {
                CarModelScreen(
                    navController = navController,
                    make = it.arguments?.getString("make")!!,
                    year = it.arguments?.getString("year")!!,
                )
            }

            composable(
                route = Screen.CarTrimScreen.route + "/{modelName}/{modelId}/{year}",
                enterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                    )
                },
                popExitTransition = {
                    return@composable slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                    )
                },
                arguments = listOf(
                    navArgument("modelName") {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("modelId") {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("year") {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )
            ) {
                CarTrimScreen(
                    navController = navController,
                    modelName = it.arguments?.getString("modelName")!!,
                    year = it.arguments?.getString("year")!!,
                    modelId = it.arguments?.getString("modelId")!!
                )
            }
        }
    }
}