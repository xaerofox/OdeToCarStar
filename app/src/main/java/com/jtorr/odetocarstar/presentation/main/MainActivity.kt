package com.jtorr.odetocarstar.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.jtorr.odetocarstar.presentation.makes.VelocityMakeScreen
import com.jtorr.odetocarstar.presentation.models.CarModelScreen
import com.jtorr.odetocarstar.presentation.trims.CarTrimScreen
import com.jtorr.odetocarstar.presentation.util.LocalSharedTransitionContext
import com.jtorr.odetocarstar.presentation.util.SharedTransitionContext
import com.jtorr.odetocarstar.presentation.util.route.Screen
import com.jtorr.odetocarstar.presentation.util.theme.OdeToCarStarTheme
import com.jtorr.odetocarstar.presentation.year.CarYearScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OdeToCarStarTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    SharedTransitionLayout {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.CarMakeScreen.route
                        ) {
                            myNavGraph(navController,this@SharedTransitionLayout)
                        }
                    }
                }
            }
        }
    }

    fun NavGraphBuilder.myNavGraph(navController: NavController, sharedTransitionScope: SharedTransitionScope) {
        navigation(
            startDestination = "CarMakeScreen", route = Screen.CarMakeScreen.route
        ) {
            composable(
                "CarMakeScreen",
            ) {
                val context = SharedTransitionContext(
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = this
                )

                CompositionLocalProvider(LocalSharedTransitionContext provides context) {
                    VelocityMakeScreen(navController)
                }
            }
            
            composable(
                Screen.CarYearScreen.route + "/{makeId}",
            ) { entry ->
                val context = SharedTransitionContext(
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = this
                )

                CompositionLocalProvider(LocalSharedTransitionContext provides context) {
                    CarYearScreen(
                        make = entry.arguments?.getString("makeId")!!,
                        navController = navController
                    )
                }
            }

            composable(
                route = Screen.CarModelScreen.route + "/{make}/{year}",
                enterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
                    )
                },
                popExitTransition = {
                    return@composable slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.End, tween(500)
                    )
                },
                popEnterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.End, tween(500)
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
                        AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
                    )
                },
                popExitTransition = {
                    return@composable slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.End, tween(500)
                    )
                },
                popEnterTransition = {
                    return@composable slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.End, tween(500)
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