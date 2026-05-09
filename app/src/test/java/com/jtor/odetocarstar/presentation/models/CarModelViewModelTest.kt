package com.jtor.odetocarstar.presentation.models

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jtor.odetocarstar.core.Resource
import com.jtor.odetocarstar.data.model.CarModel
import com.jtor.odetocarstar.domain.usecase.GetModelsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CarModelViewModelTest {

    private lateinit var viewModel: CarModelViewModel
    private lateinit var getModelsUseCase: GetModelsUseCase

    @Before
    fun setUp() {
        getModelsUseCase = mockk(relaxed = true)
        viewModel = CarModelViewModel(getModelsUseCase)
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
        coEvery { getModelsUseCase.invoke(any(), any()) } returns flowOf(Resource.Success(models))

        viewModel.getModels(2024, "Ford")

        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(models, viewModel.state.value.models)
        assertEquals("", viewModel.state.value.error)
    }

    @Test
    fun `test getModels loading`() = runTest {
        coEvery { getModelsUseCase.invoke(any(), any()) } returns flowOf(Resource.Loading())

        viewModel.getModels(2024, "Ford")

        assertEquals(true, viewModel.state.value.isLoading)
        assertEquals(emptyList<CarModel>(), viewModel.state.value.models)
        assertEquals("", viewModel.state.value.error)
    }

    @Test
    fun `test getModels error`() = runTest {
        val errorMessage = "Network error"
        coEvery {
            getModelsUseCase.invoke(any(), any())
        } returns flowOf(Resource.Error(errorMessage))

        viewModel.getModels(2024, "Ford")

        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(emptyList<CarModel>(), viewModel.state.value.models)
        assertEquals(errorMessage, viewModel.state.value.error)
    }

    @Test
    fun `test getModels with empty list`() = runTest {
        coEvery { getModelsUseCase.invoke(any(), any()) } returns flowOf(Resource.Success(emptyList()))

        viewModel.getModels(2024, "Ford")

        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(emptyList<CarModel>(), viewModel.state.value.models)
        assertEquals("", viewModel.state.value.error)
    }

    @Test
    fun `verify getModels is called with correct parameters`() = runTest {
        val year = 2024
        val make = "Ford"
        coEvery {
            getModelsUseCase(
                year,
                make
            )
        } returns flowOf(Resource.Success(emptyList()))

        viewModel.getModels(year, make)

        verify { getModelsUseCase(year, make) }
    }

    @Test
    fun `test getModels with single model`() = runTest {
        val model = CarModel(id = 1, makeId = 1, name = "Explorer")
        coEvery { getModelsUseCase.invoke(any(), any()) } returns flowOf(Resource.Success(listOf(model)))

        viewModel.getModels(2024, "Ford")

        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(listOf(model), viewModel.state.value.models)
        assertEquals("", viewModel.state.value.error)
    }
}
