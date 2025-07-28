package com.jtor.odetocarstar.presentation.util.route

sealed class Screen(val route: String) {
    data object CarMakeScreen: Screen("car_makes")
    data object CarYearScreen: Screen("car_year")
    data object CarModelScreen: Screen("car_models")
    data object CarTrimScreen: Screen("car_trim")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
