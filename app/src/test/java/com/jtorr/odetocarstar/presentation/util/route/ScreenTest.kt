package com.jtorr.odetocarstar.presentation.util.route

import org.junit.Assert.assertEquals
import org.junit.Test

class ScreenTest {

    @Test
    fun `CarMakeScreen has correct route`() {
        assertEquals("car_makes", Screen.CarMakeScreen.route)
    }

    @Test
    fun `CarYearScreen has correct route`() {
        assertEquals("car_year", Screen.CarYearScreen.route)
    }

    @Test
    fun `CarModelScreen has correct route`() {
        assertEquals("car_models", Screen.CarModelScreen.route)
    }

    @Test
    fun `CarTrimScreen has correct route`() {
        assertEquals("car_trim", Screen.CarTrimScreen.route)
    }

    @Test
    fun `withArgs returns route when no args provided`() {
        // Given
        val screen = Screen.CarMakeScreen

        // When
        val result = screen.withArgs()

        // Then
        assertEquals("car_makes", result)
    }

    @Test
    fun `withArgs appends single arg`() {
        // Given
        val screen = Screen.CarMakeScreen

        // When
        val result = screen.withArgs("1")

        // Then
        assertEquals("car_makes/1", result)
    }

    @Test
    fun `withArgs appends multiple args`() {
        // Given
        val screen = Screen.CarModelScreen

        // When
        val result = screen.withArgs("2024", "Ford")

        // Then
        assertEquals("car_models/2024/Ford", result)
    }

    @Test
    fun `withArgs appends many args`() {
        // Given
        val screen = Screen.CarTrimScreen

        // When
        val result = screen.withArgs("1", "2024", "Ford", "F-150")

        // Then
        assertEquals("car_trim/1/2024/Ford/F-150", result)
    }

    @Test
    fun `withArgs does not add trailing slash`() {
        // Given
        val screen = Screen.CarYearScreen

        // When
        val result = screen.withArgs("2024")

        // Then
        assertEquals("car_year/2024", result)
        assertEquals(false, result.endsWith("/"))
    }

    @Test
    fun `all screens have unique routes`() {
        val routes = setOf(
            Screen.CarMakeScreen.route,
            Screen.CarYearScreen.route,
            Screen.CarModelScreen.route,
            Screen.CarTrimScreen.route
        )

        assertEquals(4, routes.size)
    }
}
