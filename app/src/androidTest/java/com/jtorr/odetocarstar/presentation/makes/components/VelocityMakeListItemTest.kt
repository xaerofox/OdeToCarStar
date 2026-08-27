package com.jtorr.odetocarstar.presentation.makes.components

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.test.FakeImageLoaderEngine
import com.jtorr.odetocarstar.data.model.CarMake
import com.jtorr.odetocarstar.presentation.util.theme.OdeToCarStarTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * These tests render the real [VelocityMakeListItem], which resolves an image
 * URL via `findMakeLogo` and hands it to Coil's `SubcomposeAsyncImage`. To keep
 * the test hermetic (no real network I/O, no Espresso idling-resource hang on
 * the indeterminate loading spinner) a [FakeImageLoaderEngine] is installed via
 * [LocalImageLoader] so every image request resolves synchronously to a fake
 * drawable instead of touching the network.
 */
@Suppress("DEPRECATION") // LocalImageLoader is deprecated by Coil but still the supported
// mechanism for overriding the ImageLoader within a composition for tests on Coil 2.x.
@OptIn(ExperimentalCoilApi::class) // FakeImageLoaderEngine is Coil's supported test-only API.
class VelocityMakeListItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val fakeImageLoader: ImageLoader by lazy {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val engine = FakeImageLoaderEngine.Builder()
            .default(ColorDrawable(Color.BLUE))
            .build()
        ImageLoader.Builder(context)
            .components { add(engine) }
            .build()
    }

    private fun setContentWithFakeImageLoader(make: CarMake, onItemClick: (CarMake) -> Unit) {
        composeTestRule.setContent {
            CompositionLocalProvider(LocalImageLoader provides fakeImageLoader) {
                OdeToCarStarTheme {
                    VelocityMakeListItem(make = make, onItemClick = onItemClick)
                }
            }
        }
    }

    @Test
    fun makeName_isDisplayedUppercase() {
        val make = CarMake(id = 0, name = "Ford")

        setContentWithFakeImageLoader(make = make, onItemClick = {})

        composeTestRule.onNodeWithText("FORD").assertIsDisplayed()
    }

    @Test
    fun clickingItem_invokesCallbackWithMake() {
        val make = CarMake(id = 1, name = "Honda")
        var clicked: CarMake? = null

        setContentWithFakeImageLoader(make = make, onItemClick = { clicked = it })

        composeTestRule.onNodeWithText("HONDA").performClick()

        assertEquals(make, clicked)
    }
}
