package com.jtor.odetocarstar.presentation.util

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.staticCompositionLocalOf

@OptIn(ExperimentalSharedTransitionApi::class)
data class SharedTransitionContext(
    val sharedTransitionScope: SharedTransitionScope,
    val animatedVisibilityScope: AnimatedVisibilityScope
)

val LocalSharedTransitionContext = staticCompositionLocalOf<SharedTransitionContext?> { null }