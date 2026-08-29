package com.jtorr.odetocarstar.presentation.util.route

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
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

    // `rememberNavBackStack` persists the back stack across process death by resolving a
    // serializer reflectively from each entry's runtime class (kotlinx.serialization's
    // `serializer(value::class)`). Every Screen member MUST stay annotated `@Serializable` or
    // the app crashes at runtime on restore. These round-trips exercise that invariant for real:
    // if `@Serializable` is removed from any of the members below, `Json.encodeToString` can no
    // longer resolve a serializer for it and this file fails to compile, failing the build.
    @Test
    fun `CarMakeScreen round-trips through serialization`() {
        val screen = Screen.CarMakeScreen

        val encoded = Json.encodeToString(screen)
        val decoded = Json.decodeFromString<Screen.CarMakeScreen>(encoded)

        assertEquals(screen, decoded)
    }

    @Test
    fun `CarYearScreen round-trips through serialization`() {
        val screen = Screen.CarYearScreen(makeId = "Ford")

        val encoded = Json.encodeToString(screen)
        val decoded = Json.decodeFromString<Screen.CarYearScreen>(encoded)

        assertEquals(screen, decoded)
    }

    @Test
    fun `CarModelScreen round-trips through serialization`() {
        val screen = Screen.CarModelScreen(make = "Ford", year = "2020")

        val encoded = Json.encodeToString(screen)
        val decoded = Json.decodeFromString<Screen.CarModelScreen>(encoded)

        assertEquals(screen, decoded)
    }

    @Test
    fun `CarTrimScreen round-trips through serialization`() {
        val screen = Screen.CarTrimScreen(modelName = "F-150", modelId = "1", year = "2020")

        val encoded = Json.encodeToString(screen)
        val decoded = Json.decodeFromString<Screen.CarTrimScreen>(encoded)

        assertEquals(screen, decoded)
    }
}
