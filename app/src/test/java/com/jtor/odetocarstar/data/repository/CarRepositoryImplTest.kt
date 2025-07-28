package com.jtor.odetocarstar.data.repository

import com.jtor.odetocarstar.data.model.CarMake
import com.jtor.odetocarstar.data.model.CarModel
import com.jtor.odetocarstar.data.model.CarTrim
import com.jtor.odetocarstar.data.model.CarTrimDetail
import com.jtor.odetocarstar.data.remote.dto.CollectionDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CarRepositoryImplTest {

    private lateinit var carApi: CarApi
    private lateinit var carRepository: CarRepositoryImpl

    @Before
    fun setUp() {
        carApi = mockk()
        carRepository = CarRepositoryImpl(carApi)
    }

    @Test
    fun `getMakes returns list of car makes on success`() = runBlocking {
        // Given
        val year = 2020
        val sort = "asc"
        val expectedCarMakes = listOf(CarMake(id = 1, name = "Toyota"))
        // Assuming your CollectionDto has a structure that can be directly used
        // or has a property like 'data' or 'items' that holds the list.
        // For this example, I'll assume CollectionDto has a 'data' property
        // and a 'collection' property as seen in your shared file snippet.
        // You might need to adjust this based on the actual CollectionDto structure.
        val apiResponse = CollectionDto(
            collection = mockk(), // Mock or create a dummy Collection object if needed
            data = expectedCarMakes
        )

        coEvery { carApi.getMakes(year, sort) } returns apiResponse

        // When
        val result = carRepository.getMakes(year, sort)

        // Then
        assertEquals(expectedCarMakes, result)
        coVerify(exactly = 1) { carApi.getMakes(year, sort) }
    }

    @Test
    fun `getMakes uses default year when year is null`() = runBlocking {
        // Given
        val sort = "asc"
        val defaultYear = 2015 // As per your repository logic
        val expectedCarMakes = listOf(CarMake(id = 1, name = "Honda"))
        val apiResponse = CollectionDto(
            collection = mockk(),
            data = expectedCarMakes
        )

        coEvery { carApi.getMakes(defaultYear, sort) } returns apiResponse

        // When
        val result = carRepository.getMakes(null, sort)

        // Then
        assertEquals(expectedCarMakes, result)
        coVerify(exactly = 1) { carApi.getMakes(defaultYear, sort) }
    }

    @Test
    fun `getModels returns list of car models on success`() = runBlocking {
        // Given
        val year = 2021
        val make = "Ford"
        val expectedCarModels = listOf(CarModel(id = 1, name = "Mustang", makeId = 1))
        val apiResponse = CollectionDto(
            collection = mockk(),
            data = expectedCarModels
        )

        coEvery { carApi.getModels(year, make) } returns apiResponse

        // When
        val result = carRepository.getModels(year, make)

        // Then
        assertEquals(expectedCarModels, result)
        coVerify(exactly = 1) { carApi.getModels(year, make) }
    }

    @Test
    fun `getTrims returns list of car trims on success`() = runBlocking {
        // Given
        val year = 2022
        val modelId = 10
        val expectedCarTrims = listOf(
            CarTrim(
                id = 1,
                name = "GT",
                description = "Grand Touring",
                msrp = 40000,
                invoice = 38000,
                modelId = 10,
                year = 2022,
                created = "2023-01-01T00:00:00Z",
                modified = "2023-01-01T00:00:00Z"
            )
        )
        val apiResponse = CollectionDto(
            collection = mockk(),
            data = expectedCarTrims
        )

        coEvery { carApi.getTrims(year, modelId) } returns apiResponse

        // When
        val result = carRepository.getTrims(year, modelId)

        // Then
        assertEquals(expectedCarTrims, result)
        coVerify(exactly = 1) { carApi.getTrims(year, modelId) }
    }

    @Test
    fun `getTrimDetail returns car trim detail on success`() = runBlocking {
        // Given
        val trimId = 100
        val expectedTrimDetail = CarTrimDetail(
            id = 100,
            modelId = 10,
            year = 2023,
            name = "LX",
            description = "Luxury Edition",
            msrp = 35000,
            invoice = 33000,
            trimInteriorColors = emptyList(),
            trimExteriorColors = emptyList(),
            trimMileage = null,
            trimEngine = null,
            trimBody = null,
            created = "2023-01-01T00:00:00Z",
            makeModel = null,
            modified = "2023-01-01T00:00:00Z"
        )
        // For getTrimDetail, the API likely returns the CarTrimDetail object directly,
        // not wrapped in CollectionDto, unless your API is structured that way.
        // I'll assume it returns CarTrimDetail directly. If it's wrapped, adjust accordingly.
        coEvery { carApi.getTrimDetail(trimId) } returns expectedTrimDetail

        // When
        val result = carRepository.getTrimDetail(trimId)

        // Then
        assertEquals(expectedTrimDetail, result)
        coVerify(exactly = 1) { carApi.getTrimDetail(trimId) }
    }
}