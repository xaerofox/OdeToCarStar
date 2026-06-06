package com.jtor.odetocarstar.data.repository

import com.jtor.odetocarstar.data.local.dao.CarMakeDao
import com.jtor.odetocarstar.data.local.dao.CarModelDao
import com.jtor.odetocarstar.data.local.dao.CarTrimDao
import com.jtor.odetocarstar.data.local.dao.CarTrimDetailDao
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
    private lateinit var carMakeDao: CarMakeDao
    private lateinit var carModelDao: CarModelDao
    private lateinit var carTrimDao: CarTrimDao
    private lateinit var carTrimDetailDao: CarTrimDetailDao
    private lateinit var carRepository: CarRepositoryImpl

    @Before
    fun setUp() {
        carApi = mockk()
        carMakeDao = mockk(relaxed = true)
        carModelDao = mockk(relaxed = true)
        carTrimDao = mockk(relaxed = true)
        carTrimDetailDao = mockk(relaxed = true)
        carRepository = CarRepositoryImpl(carApi, carMakeDao, carModelDao, carTrimDao, carTrimDetailDao)
    }

    @Test
    fun `getMakes returns list of car makes on success`() = runBlocking {
        // Given
        val year = 2020
        val sort = "asc"
        val expectedCarMakes = listOf(CarMake(id = 1, name = "Toyota"))

        coEvery { carMakeDao.getMakes(year) } returns emptyList()
        val apiResponse = CollectionDto(
            collection = mockk(),
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
        val defaultYear = 2015
        val expectedCarMakes = listOf(CarMake(id = 1, name = "Honda"))

        coEvery { carMakeDao.getMakes(defaultYear) } returns emptyList()
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

        coEvery { carMakeDao.getMakes(2015) } returns emptyList()
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

        coEvery { carTrimDao.getTrims(year, modelId) } returns emptyList()
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
        val year = 2023
        val expectedTrimDetail = CarTrimDetail(
            id = 100,
            modelId = 10,
            year = year,
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
        coEvery { carTrimDetailDao.getDetail(trimId, year) } returns null
        coEvery { carApi.getTrimDetail(trimId, year) } returns expectedTrimDetail

        // When
        val result = carRepository.getTrimDetail(trimId, year)

        // Then
        assertEquals(expectedTrimDetail, result)
        coVerify(exactly = 1) { carApi.getTrimDetail(trimId, year) }
    }
}
