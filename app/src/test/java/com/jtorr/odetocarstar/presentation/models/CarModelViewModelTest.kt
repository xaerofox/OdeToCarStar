package com.jtorr.odetocarstar.presentation.models

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jtorr.odetocarstar.data.model.CarModel
import com.jtorr.odetocarstar.data.repository.CarRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CarModelViewModelTest {

    private lateinit var viewModel: CarModelViewModel
    private lateinit var repository: CarRepository

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        viewModel = CarModelViewModel(repository)
    }

    @Test
    fun `test initial state`() {
        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(emptyList<CarModel>(), viewModel.state.value.models)
        assertEquals("", viewModel.state.value.error)
    }

    @Test
    fun `test getModels success`() = runTest {
        val models = listOf(
            CarModel(id = 1, makeId = 1, name = "F-150"),
            CarModel(id = 2, makeId = 1, name = "Mustang")
        )
        coEvery { repository.getModels(2024, "Ford") } returns models

        viewModel.getModels(2024, "Ford")

        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(models, viewModel.state.value.models)
        assertEquals("", viewModel.state.value.error)
    }

    @Test
    fun `test getModels error`() = runTest {
        val errorMessage = "Network error"
        coEvery { repository.getModels(2024, "Ford") } throws Exception(errorMessage)

        viewModel.getModels(2024, "Ford")

        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(emptyList<CarModel>(), viewModel.state.value.models)
        assertEquals(errorMessage, viewModel.state.value.error)
    }

    @Test
    fun `test getModels with empty list`() = runTest {
        coEvery { repository.getModels(2024, "Ford") } returns emptyList()

        viewModel.getModels(2024, "Ford")

        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(emptyList<CarModel>(), viewModel.state.value.models)
        assertEquals("", viewModel.state.value.error)
    }

    @Test
    fun `test getModels with single model`() = runTest {
        val model = CarModel(id = 1, makeId = 1, name = "Explorer")
        coEvery { repository.getModels(2024, "Ford") } returns listOf(model)

        viewModel.getModels(2024, "Ford")

        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(listOf(model), viewModel.state.value.models)
        assertEquals("", viewModel.state.value.error)
    }
}
