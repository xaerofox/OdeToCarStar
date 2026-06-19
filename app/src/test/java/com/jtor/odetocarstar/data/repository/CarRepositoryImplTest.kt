package com.jtor.odetocarstar.data.repository

import com.jtor.odetocarstar.data.local.dao.CarMakeDao
import com.jtor.odetocarstar.data.local.dao.CarModelDao
import com.jtor.odetocarstar.data.local.dao.CarTrimDao
import com.jtor.odetocarstar.data.local.dao.CarTrimDetailDao
import com.jtor.odetocarstar.data.local.entity.CarMakeEntity
import com.jtor.odetocarstar.data.local.entity.CarModelEntity
import com.jtor.odetocarstar.data.local.entity.CarTrimDetailEntity
import com.jtor.odetocarstar.data.local.entity.CarTrimEntity
import com.jtor.odetocarstar.data.model.CarMake
import com.jtor.odetocarstar.data.model.CarModel
import com.jtor.odetocarstar.data.model.CarTrim
import com.jtor.odetocarstar.data.model.CarTrimDetail
import com.jtor.odetocarstar.data.model.MakeModel
import com.jtor.odetocarstar.data.model.Make
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

    // ==================== getMakes ====================

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
    fun `getMakes returns cached data when DAO has results`() = runBlocking {
        // Given
        val year = 2020
        val sort = "asc"
        val cachedMakes = listOf(
            CarMake(id = 1, name = "Toyota"),
            CarMake(id = 2, name = "Honda")
        )

        coEvery { carMakeDao.getMakes(year) } returns cachedMakes.map { it.toEntity(year) }

        // When
        val result = carRepository.getMakes(year, sort)

        // Then
        assertEquals(cachedMakes, result)
        coVerify(exactly = 0) { carApi.getMakes(any(), any()) }
        coVerify(exactly = 0) { carMakeDao.insertMakes(any()) }
    }

    @Test
    fun `getMakes throws error when API fails`() = runBlocking {
        // Given
        val year = 2020
        val sort = "asc"
        val errorMessage = "API connection failed"

        coEvery { carMakeDao.getMakes(year) } returns emptyList()
        coEvery { carApi.getMakes(year, sort) } throws Exception(errorMessage)

        // When/Then
        try {
            carRepository.getMakes(year, sort)
            throw AssertionError("Expected exception was not thrown")
        } catch (e: Exception) {
            assertEquals(errorMessage, e.message)
        }
        coVerify(exactly = 1) { carMakeDao.getMakes(year) }
        coVerify(exactly = 1) { carApi.getMakes(year, sort) }
        coVerify(exactly = 0) { carMakeDao.insertMakes(any()) }
    }

    // ==================== getModels ====================

    @Test
    fun `getModels returns list of car models on success`() = runBlocking {
        // Given
        val year = 2021
        val make = "Ford"
        val expectedCarModels = listOf(CarModel(id = 1, name = "Mustang", makeId = 1))

        coEvery { carMakeDao.getMakes(year) } returns emptyList()
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
    fun `getModels returns cached data when DAO has results`() = runBlocking {
        // Given
        val year = 2020
        val make = "Toyota"
        val cachedModels = listOf(
            CarModel(id = 1, makeId = 1, name = "Camry"),
            CarModel(id = 2, makeId = 1, name = "Corolla")
        )
        val cachedMakes = listOf(CarMakeEntity(name = "Toyota", id = 1, year = year))

        coEvery { carMakeDao.getMakes(year) } returns cachedMakes
        coEvery { carModelDao.getModels(year, 1) } returns cachedModels.map { it.toEntity(year) }

        // When
        val result = carRepository.getModels(year, make)

        // Then
        assertEquals(cachedModels, result)
        coVerify(exactly = 0) { carApi.getModels(any(), any()) }
    }

    @Test
    fun `getModels throws error when API fails`() = runBlocking {
        // Given
        val year = 2021
        val make = "Ford"
        val errorMessage = "Network error"

        coEvery { carMakeDao.getMakes(year) } returns emptyList()
        coEvery { carApi.getModels(year, make) } throws Exception(errorMessage)

        // When/Then
        try {
            carRepository.getModels(year, make)
            throw AssertionError("Expected exception was not thrown")
        } catch (e: Exception) {
            assertEquals(errorMessage, e.message)
        }
        coVerify(exactly = 1) { carApi.getModels(year, make) }
    }

    // ==================== getTrims ====================

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
    fun `getTrims returns cached data when DAO has results`() = runBlocking {
        // Given
        val year = 2020
        val modelId = 5
        val cachedTrims = listOf(
            CarTrim(
                id = 10,
                name = "LX",
                description = "Luxury",
                msrp = 30000,
                invoice = 28000,
                modelId = 5,
                year = 2020,
                created = "2023-01-01T00:00:00Z",
                modified = "2023-01-01T00:00:00Z"
            )
        )

        coEvery { carTrimDao.getTrims(year, modelId) } returns cachedTrims.map { it.toEntity(year) }

        // When
        val result = carRepository.getTrims(year, modelId)

        // Then
        assertEquals(cachedTrims, result)
        coVerify(exactly = 0) { carApi.getTrims(any(), any()) }
    }

    @Test
    fun `getTrims throws error when API fails`() = runBlocking {
        // Given
        val year = 2022
        val modelId = 10
        val errorMessage = "API error"

        coEvery { carTrimDao.getTrims(year, modelId) } returns emptyList()
        coEvery { carApi.getTrims(year, modelId) } throws Exception(errorMessage)

        // When/Then
        try {
            carRepository.getTrims(year, modelId)
            throw AssertionError("Expected exception was not thrown")
        } catch (e: Exception) {
            assertEquals(errorMessage, e.message)
        }
        coVerify(exactly = 1) { carApi.getTrims(year, modelId) }
    }

    // ==================== getTrimDetail ====================

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

    @Test
    fun `getTrimDetail returns cached data when DAO has results`() = runBlocking {
        // Given
        val trimId = 50
        val year = 2021
        val cachedDetail = CarTrimDetail(
            id = 50,
            modelId = 5,
            year = year,
            name = "Sport",
            description = "Sport trim",
            msrp = 28000,
            invoice = 26000,
            trimInteriorColors = emptyList(),
            trimExteriorColors = emptyList(),
            trimMileage = null,
            trimEngine = null,
            trimBody = null,
            created = "2023-01-01T00:00:00Z",
            makeModel = null,
            modified = "2023-01-01T00:00:00Z"
        )

        coEvery { carTrimDetailDao.getDetail(trimId, year) } returns cachedDetail.toEntity(year)

        // When
        val result = carRepository.getTrimDetail(trimId, year)

        // Then
        assertEquals(cachedDetail, result)
        coVerify(exactly = 0) { carApi.getTrimDetail(any(), any()) }
    }

    @Test
    fun `getTrimDetail throws error when API fails`() = runBlocking {
        // Given
        val trimId = 100
        val year = 2023
        val errorMessage = "API error"

        coEvery { carTrimDetailDao.getDetail(trimId, year) } returns null
        coEvery { carApi.getTrimDetail(trimId, year) } throws Exception(errorMessage)

        // When/Then
        try {
            carRepository.getTrimDetail(trimId, year)
            throw AssertionError("Expected exception was not thrown")
        } catch (e: Exception) {
            assertEquals(errorMessage, e.message)
        }
        coVerify(exactly = 1) { carApi.getTrimDetail(trimId, year) }
    }

    // ==================== clearCache ====================

    @Test
    fun `clearCache calls clearAll on all DAOs`() = runBlocking {
        // Given/When
        carRepository.clearCache()

        // Then
        coVerify(exactly = 1) { carMakeDao.clearAll() }
        coVerify(exactly = 1) { carModelDao.clearAll() }
        coVerify(exactly = 1) { carTrimDao.clearAll() }
        coVerify(exactly = 1) { carTrimDetailDao.clearAll() }
    }
}
