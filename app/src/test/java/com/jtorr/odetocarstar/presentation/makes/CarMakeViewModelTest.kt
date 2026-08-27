package com.jtorr.odetocarstar.presentation.makes

import com.jtorr.odetocarstar.data.model.CarMake
import com.jtorr.odetocarstar.data.repository.CarRepository
import com.jtorr.odetocarstar.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CarMakeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: CarMakeViewModel
    private lateinit var repository: CarRepository

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        viewModel = CarMakeViewModel(repository)
    }

    @Test
    fun `test initial state`() = runTest {
        assertTrue(viewModel.state.value is MakeListState.Loading)
        coVerify(exactly = 0) { repository.getMakes(any(), any()) }
    }

    @Test
    fun `test getMakes success`() = runTest {
        val makes = listOf(CarMake(id = 1, name = "Toyota"), CarMake(id = 2, name = "Honda"))
        coEvery { repository.getMakes(2015, "id") } returns makes

        viewModel.getMakes()

        val state = viewModel.state.value
        assertTrue(state is MakeListState.Success)
        assertEquals(makes, (state as MakeListState.Success).makes)
    }

    @Test
    fun `test getMakes error`() = runTest {
        val errorMessage = "Network error"
        coEvery { repository.getMakes(2015, "id") } throws Exception(errorMessage)

        viewModel.getMakes()

        val state = viewModel.state.value
        assertTrue(state is MakeListState.Error)
        assertEquals(errorMessage, (state as MakeListState.Error).message)
    }

    @Test
    fun `test getMakes called twice only invokes repository once`() = runTest {
        val makes = listOf(CarMake(id = 1, name = "Toyota"))
        coEvery { repository.getMakes(2015, "id") } returns makes

        viewModel.getMakes()
        viewModel.getMakes()

        coVerify(exactly = 1) { repository.getMakes(2015, "id") }
        assertEquals(makes, (viewModel.state.value as MakeListState.Success).makes)
    }

    @Test
    fun `test getMakes does not regress from Success back to Loading on redundant call`() = runTest {
        val makes = listOf(CarMake(id = 1, name = "Toyota"))
        coEvery { repository.getMakes(2015, "id") } returns makes

        viewModel.getMakes()
        assertTrue(viewModel.state.value is MakeListState.Success)

        viewModel.getMakes()

        assertTrue(viewModel.state.value is MakeListState.Success)
        assertEquals(makes, (viewModel.state.value as MakeListState.Success).makes)
    }

    /**
     * Genuinely exercises the `loadJob?.isActive == true` branch of the guard
     * in [CarMakeViewModel.getMakes]. Unlike the other tests in this class,
     * this one swaps in a [StandardTestDispatcher] (instead of the shared
     * [MainDispatcherRule]'s [kotlinx.coroutines.test.UnconfinedTestDispatcher])
     * combined with a suspending repository stub, so the first `getMakes()`
     * call is left suspended mid-flight (not yet Success) when the second
     * call is made. If the `loadJob?.isActive` guard were removed, the second
     * call would launch its own coroutine and invoke the repository a second
     * time, and the `coVerify(exactly = 1)` assertion below would fail.
     */
    @Test
    fun `test getMakes ignores concurrent call while a load is still in flight`() = runTest {
        val standardDispatcher = StandardTestDispatcher(testScheduler)
        Dispatchers.setMain(standardDispatcher)

        val makes = listOf(CarMake(id = 1, name = "Toyota"))
        coEvery { repository.getMakes(2015, "id") } coAnswers {
            delay(100)
            makes
        }

        viewModel.getMakes()
        // Let the first coroutine start and suspend at the delay, without
        // resolving it, so the job is genuinely in flight (not yet Success).
        runCurrent()
        assertTrue(viewModel.state.value is MakeListState.Loading)

        viewModel.getMakes()
        // Give a would-be second in-flight coroutine (if the guard were
        // removed) a chance to actually reach the repository call, so this
        // assertion can't pass merely because the second job hasn't been
        // dispatched yet.
        runCurrent()

        coVerify(exactly = 1) { repository.getMakes(any(), any()) }

        advanceUntilIdle()

        assertTrue(viewModel.state.value is MakeListState.Success)
        assertEquals(makes, (viewModel.state.value as MakeListState.Success).makes)
    }
}
