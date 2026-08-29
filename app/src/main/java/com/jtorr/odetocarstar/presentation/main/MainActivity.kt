package com.jtorr.odetocarstar.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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

/**
 * Builds push/pop transition metadata for a slide-in entry.
 *
 * Push: the entry itself slides in from the right while the entry it covers fades out
 * (matching the navigation-compose default for an unset `exitTransition`).
 * Pop: the entry itself slides back out to the right while the entry it reveals plays
 * [popRevealEnter] (that revealed entry's own "no custom transition" behavior).
 */
private fun slideTransitionMetadata(popRevealEnter: EnterTransition) = metadata {
    put(NavDisplay.TransitionKey) {
        slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(500)
        ) togetherWith fadeOut(animationSpec = tween(700))
    }
    put(NavDisplay.PopTransitionKey) {
        popRevealEnter togetherWith
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(500)
            )
    }
}

// CarModelScreen's pop reveals CarYearScreen, which has no custom transition (default fade in).
private val CAR_MODEL_TRANSITION_METADATA = slideTransitionMetadata(
    popRevealEnter = fadeIn(animationSpec = tween(700))
)

// CarTrimScreen's pop reveals CarModelScreen, which slides in from the right on push, so its
// pop-reveal parallaxes in from the left.
private val CAR_TRIM_TRANSITION_METADATA = slideTransitionMetadata(
    popRevealEnter = slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(500))
)

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
                                    metadata = CAR_MODEL_TRANSITION_METADATA
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
                                    metadata = CAR_TRIM_TRANSITION_METADATA
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
