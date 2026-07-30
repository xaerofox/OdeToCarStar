package com.jtorr.odetocarstar.presentation.trims

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jtorr.odetocarstar.data.model.CarTrim
import com.jtorr.odetocarstar.data.model.FakeTestData
import com.jtorr.odetocarstar.data.repository.CarRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CarTrimViewModelTest {

    private lateinit var viewModel: CarTrimViewModel
    private lateinit var repository: CarRepository

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        viewModel = CarTrimViewModel(repository)
    }

    @Test
    fun `test initial trimming list state`() {
        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(emptyList<CarTrim>(), viewModel.state.value.trims)
        assertEquals("", viewModel.state.value.error)
    }

    @Test
    fun `test initial detail state`() {
        assertEquals(false, viewModel.detailState.value.isLoading)
        assertEquals(null, viewModel.detailState.value.detail)
        assertEquals("", viewModel.detailState.value.error)
    }

    @Test
    fun `test getTrims success`() = runTest {
        val mockTrims = FakeTestData.getCarsTrims()
        coEvery { repository.getTrims(2024, 1) } returns mockTrims

        viewModel.getTrims(2024, 1)

        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(mockTrims, viewModel.state.value.trims)
        assertEquals("", viewModel.state.value.error)
    }

    @Test
    fun `test getTrims error`() = runTest {
        val errorMessage = "Network error"
        coEvery { repository.getTrims(2024, 1) } throws Exception(errorMessage)

        viewModel.getTrims(2024, 1)

        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(emptyList<CarTrim>(), viewModel.state.value.trims)
        assertEquals(errorMessage, viewModel.state.value.error)
    }

    @Test
    fun `test getTrimDetail success`() = runTest {
        val detail = FakeTestData.getCarTrimDetail()
        coEvery { repository.getTrimDetail(any(), any()) } returns detail

        viewModel.getTrimDetail(1, 2024)

        assertEquals(false, viewModel.detailState.value.isLoading)
        assertEquals(detail, viewModel.detailState.value.detail)
        assertEquals("", viewModel.detailState.value.error)
    }

    @Test
    fun `test getTrimDetail failure`() = runTest {
        val error = Exception("Network error")
        coEvery { repository.getTrimDetail(any(), any()) } throws error

        viewModel.getTrimDetail(1, 2024)

        assertEquals(false, viewModel.detailState.value.isLoading)
        assertEquals(null, viewModel.detailState.value.detail)
        assertEquals(error.message, viewModel.detailState.value.error)
    }
}
