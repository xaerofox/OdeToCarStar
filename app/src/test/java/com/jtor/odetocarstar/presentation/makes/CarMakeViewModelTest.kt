package com.jtor.odetocarstar.presentation.makes

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jtor.odetocarstar.core.Resource
import com.jtor.odetocarstar.data.model.CarMake
import com.jtor.odetocarstar.domain.usecase.GetMakesUseCase
import io.mockk.coEvery
import io.mockk.every
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
        getMakesUseCase = mockk()
        viewModel = CarMakeViewModel(getMakesUseCase)
    }

    @Test
    fun `test initial state`() {
        assertEquals(true, viewModel.state.value.isLoading)
        assertEquals(emptyList<CarMake>(), viewModel.state.value.makes)
        assertEquals("", viewModel.state.value.error)
    }

    @Test
    fun `test getMakes success`() = runTest {
        val makes = listOf(CarMake(id = 1, name = "Toyota"), CarMake(id = 2, name = "Honda"))
        coEvery { getMakesUseCase() } returns flowOf(Resource.Success(makes))


        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(makes, viewModel.state.value.makes)
        assertEquals("", viewModel.state.value.error)
    }

    @Test
    fun `test getMakes loading`() = runTest {
        every { getMakesUseCase() } returns flowOf(Resource.Loading())

        assertEquals(true, viewModel.state.value.isLoading)
        assertEquals(emptyList<CarMake>(), viewModel.state.value.makes)
        assertEquals("", viewModel.state.value.error)
    }

    @Test
    fun `test getMakes error`() = runTest {
        val errorMessage = "Network error"
        coEvery { getMakesUseCase() } returns flowOf(Resource.Error(errorMessage))

        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(emptyList<CarMake>(), viewModel.state.value.makes)
        assertEquals(errorMessage, viewModel.state.value.error)
    }
}
