package com.jtorr.odetocarstar.presentation.util.route

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Screen : NavKey {
    @Serializable
    data object CarMakeScreen : Screen

    @Serializable
    data class CarYearScreen(val makeId: String) : Screen

    @Serializable
    data class CarModelScreen(val make: String, val year: String) : Screen

    @Serializable
    data class CarTrimScreen(val modelName: String, val modelId: String, val year: String) : Screen
}
