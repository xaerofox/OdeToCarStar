package com.jtorr.odetocarstar.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.metadata
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import androidx.navigation3.ui.NavDisplay
import com.jtorr.odetocarstar.presentation.makes.VelocityMakeScreen
import com.jtorr.odetocarstar.presentation.models.CarModelScreen
import com.jtorr.odetocarstar.presentation.trims.CarTrimScreen
import com.jtorr.odetocarstar.presentation.util.LocalSharedTransitionContext
import com.jtorr.odetocarstar.presentation.util.SharedTransitionContext
import com.jtorr.odetocarstar.presentation.util.route.Screen
import com.jtorr.odetocarstar.presentation.util.theme.OdeToCarStarTheme
import com.jtorr.odetocarstar.presentation.year.CarYearScreen
import dagger.hilt.android.AndroidEntryPoint

private val SLIDE_TRANSITION_METADATA = metadata {
    put(NavDisplay.TransitionKey) {
        slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(500)
        ) togetherWith ExitTransition.KeepUntilTransitionsFinished
    }
    put(NavDisplay.PopTransitionKey) {
        EnterTransition.None togetherWith
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(500)
            )
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OdeToCarStarTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val backStack = rememberNavBackStack(Screen.CarMakeScreen)

                    SharedTransitionLayout {
                        NavDisplay(
                            backStack = backStack,
                            onBack = { backStack.removeLastOrNull() },
                            sharedTransitionScope = this,
                            entryDecorators = listOf(
                                rememberSaveableStateHolderNavEntryDecorator(),
                                rememberViewModelStoreNavEntryDecorator()
                            ),
                            entryProvider = entryProvider {
                                entry<Screen.CarMakeScreen> {
                                    val context = SharedTransitionContext(
                                        sharedTransitionScope = this@SharedTransitionLayout,
                                        animatedVisibilityScope = LocalNavAnimatedContentScope.current
                                    )

                                    CompositionLocalProvider(LocalSharedTransitionContext provides context) {
                                        VelocityMakeScreen(
                                            onMakeClick = { make ->
                                                backStack.add(Screen.CarYearScreen(makeId = make.name))
                                            }
                                        )
                                    }
                                }

                                entry<Screen.CarYearScreen> { key ->
                                    val context = SharedTransitionContext(
                                        sharedTransitionScope = this@SharedTransitionLayout,
                                        animatedVisibilityScope = LocalNavAnimatedContentScope.current
                                    )

                                    CompositionLocalProvider(LocalSharedTransitionContext provides context) {
                                        CarYearScreen(
                                            make = key.makeId,
                                            onBackClick = { backStack.removeLastOrNull() },
                                            onYearClick = { year ->
                                                backStack.add(
                                                    Screen.CarModelScreen(make = key.makeId, year = year)
                                                )
                                            }
                                        )
                                    }
                                }

                                entry<Screen.CarModelScreen>(
                                    metadata = SLIDE_TRANSITION_METADATA
                                ) { key ->
                                    CarModelScreen(
                                        make = key.make,
                                        year = key.year,
                                        onBackClick = { backStack.removeLastOrNull() },
                                        onModelClick = { model ->
                                            backStack.add(
                                                Screen.CarTrimScreen(
                                                    modelName = model.name,
                                                    modelId = model.id.toString(),
                                                    year = key.year
                                                )
                                            )
                                        }
                                    )
                                }

                                entry<Screen.CarTrimScreen>(
                                    metadata = SLIDE_TRANSITION_METADATA
                                ) { key ->
                                    CarTrimScreen(
                                        modelName = key.modelName,
                                        year = key.year,
                                        modelId = key.modelId,
                                        onBackClick = { backStack.removeLastOrNull() }
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
