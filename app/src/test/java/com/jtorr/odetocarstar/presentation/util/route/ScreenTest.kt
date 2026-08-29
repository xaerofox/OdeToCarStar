package com.jtorr.odetocarstar.presentation.util.route

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertSame
import org.junit.Test

class ScreenTest {

    @Test
    fun `CarYearScreen carries the selected make`() {
        val screen = Screen.CarYearScreen(makeId = "Ford")

        assertEquals("Ford", screen.makeId)
    }

    @Test
    fun `CarModelScreen carries make and year`() {
        val screen = Screen.CarModelScreen(make = "Ford", year = "2020")

        assertEquals("Ford", screen.make)
        assertEquals("2020", screen.year)
    }

    @Test
    fun `CarTrimScreen carries model name, id and year`() {
        val screen = Screen.CarTrimScreen(modelName = "F-150", modelId = "1", year = "2020")

        assertEquals("F-150", screen.modelName)
        assertEquals("1", screen.modelId)
        assertEquals("2020", screen.year)
    }

    @Test
    fun `screens with the same arguments are equal`() {
        assertEquals(Screen.CarYearScreen(makeId = "Ford"), Screen.CarYearScreen(makeId = "Ford"))
        assertEquals(
            Screen.CarModelScreen(make = "Ford", year = "2020"),
            Screen.CarModelScreen(make = "Ford", year = "2020")
        )
    }

    @Test
    fun `screens with different arguments are not equal`() {
        assertNotEquals(Screen.CarYearScreen(makeId = "Ford"), Screen.CarYearScreen(makeId = "Honda"))
    }

    @Test
    fun `CarMakeScreen is a singleton object`() {
        assertSame(Screen.CarMakeScreen, Screen.CarMakeScreen)
    }
}
