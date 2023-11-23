package com.jtor.odetocarstar.presentation

sealed class Screen(val route: String) {
    object CarMakeScreen: Screen("car_makes")
}
