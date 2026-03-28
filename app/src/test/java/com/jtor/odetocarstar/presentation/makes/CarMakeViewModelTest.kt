package com.jtor.odetocarstar.presentation.makes

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jtor.odetocarstar.core.Resource
import com.jtor.odetocarstar.data.model.CarMake
import com.jtor.odetocarstar.domain.usecase.GetMakesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CarMakeViewModelTest {

    private lateinit var viewModel: CarMakeViewModel
    private lateinit var getMakesUseCase: GetMakesUseCase

    @Before
    fun setUp() {
        getMakesUseCase = mockk(relaxed = true)
        viewModel = CarMakeViewModel(getMakesUseCase)
    }

    @Test
    fun `test initial state`() {
        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(emptyList<CarMake>(), viewModel.state.value.makes)
        assertEquals("", viewModel.state.value.error)
    }

    @Test
    fun `test getMakes success`() = runTest {
        val makes = listOf(CarMake(id = 1, name = "Toyota"), CarMake(id = 2, name = "Honda"))
        coEvery { getMakesUseCase.invoke() } returns flowOf(Resource.Success(makes))

        // Trigger the use case and collect the state
        viewModel.getMakes()

        // Verify the state is updated correctly
        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(makes, viewModel.state.value.makes)
        assertEquals("", viewModel.state.value.error)
    }

    @Test
    fun `test getMakes loading`() = runTest {
        coEvery { getMakesUseCase.invoke() } returns flowOf(Resource.Loading())

        // Trigger the use case and collect the state
        viewModel.getMakes()

        assertEquals(true, viewModel.state.value.isLoading)
        assertEquals(emptyList<CarMake>(), viewModel.state.value.makes)
        assertEquals("", viewModel.state.value.error)
    }

    @Test
    fun `test getMakes error`() = runTest {
        val errorMessage = "Network error"
        coEvery { getMakesUseCase.invoke() } returns flowOf(Resource.Error(errorMessage))

        // Trigger the use case and collect the state
        viewModel.getMakes()

        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(emptyList<CarMake>(), viewModel.state.value.makes)
        assertEquals(errorMessage, viewModel.state.value.error)
    }
}