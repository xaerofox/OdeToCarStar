package com.jtor.odetocarstar.presentation

sealed class Screen(val route: String) {
    object CarMakeScreen: Screen("car_makes")
    object CarYearScreen: Screen("car_year")
    object CarModelScreen: Screen("car_models")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
