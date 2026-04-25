package com.jtor.odetocarstar.presentation.trims

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jtor.odetocarstar.core.Resource
import com.jtor.odetocarstar.data.model.CarTrim
import com.jtor.odetocarstar.data.model.CarTrimDetail
import com.jtor.odetocarstar.data.model.FakeTestData
import com.jtor.odetocarstar.data.repository.CarRepository
import com.jtor.odetocarstar.domain.usecase.GetTrimsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CarTrimViewModelTest {

    private lateinit var viewModel: CarTrimViewModel
    private lateinit var getTrimsUseCase: GetTrimsUseCase
    private lateinit var repository: CarRepository

    @Before
    fun setUp() {
        getTrimsUseCase = mockk(relaxed = true)
        repository = mockk(relaxed = true)
        viewModel = CarTrimViewModel(getTrimsUseCase, repository)
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
        coEvery { getTrimsUseCase.invoke(any(), any()) } returns flowOf(Resource.Success(mockTrims))

        viewModel.getTrims(2024, 1)

        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(mockTrims, viewModel.state.value.trims)
        assertEquals("", viewModel.state.value.error)
    }

    @Test
    fun `test getTrims loading`() = runTest {
        coEvery { getTrimsUseCase.invoke(any(), any()) } returns flowOf(Resource.Loading())

        viewModel.getTrims(2024, 1)

        assertEquals(true, viewModel.state.value.isLoading)
        assertEquals(emptyList<CarTrim>(), viewModel.state.value.trims)
        assertEquals("", viewModel.state.value.error)
    }

    @Test
    fun `test getTrims error`() = runTest {
        val errorMessage = "Network error"
        coEvery {
            getTrimsUseCase.invoke(
                any(),
                any()
            )
        } returns flowOf(Resource.Error(errorMessage))

        viewModel.getTrims(2024, 1)

        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(emptyList<CarTrim>(), viewModel.state.value.trims)
        assertEquals(errorMessage, viewModel.state.value.error)
    }

    @Test
    fun `test getTrimDetail success`() = runTest {
        val detail = FakeTestData.getCarTrimDetail()
        coEvery { repository.getTrimDetail(any()) } answers { detail }

        viewModel.getTrimDetail(1)

        assertEquals(true, viewModel.detailState.value.isLoading)
        assertEquals(detail, viewModel.detailState.value.detail)
        assertEquals("", viewModel.detailState.value.error)
    }

    @Test
    fun `test getTrimDetail failure`() = runTest {
        val error = IOException("Network error")
        coEvery { repository.getTrimDetail(any()) } throws error

        viewModel.getTrimDetail(1)

        assertEquals(false, viewModel.detailState.value.isLoading)
        assertEquals(null, viewModel.detailState.value.detail)
        assertEquals(error.message, viewModel.detailState.value.error)
    }

    @Test
    fun `verify getTrims is called with correct parameters`() = runTest {
        val year = FakeTestData.getCarsTrims().last().year
        val modelId = FakeTestData.getCarsTrims().last().modelId
        coEvery {
            getTrimsUseCase.invoke(
                year,
                modelId
            )
        } returns flowOf(Resource.Success(emptyList()))

        viewModel.getTrims(year, modelId)

        verify { getTrimsUseCase.invoke(year, modelId) }
    }
}
