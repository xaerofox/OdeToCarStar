package com.jtor.odetocarstar.data.repository

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
import com.jtor.odetocarstar.data.model.TrimBody
import com.jtor.odetocarstar.data.model.TrimEngine
import com.jtor.odetocarstar.data.model.TrimMileage
import com.jtor.odetocarstar.data.model.TrimColor
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperFunctionsTest {

    // ==================== CarMake mappers ====================

    @Test
    fun `CarMake toEntity creates correct CarMakeEntity`() {
        // Given
        val carMake = CarMake(id = 1, name = "Toyota")
        val year = 2020

        // When
        val entity = carMake.toEntity(year)

        // Then
        assertEquals(carMake.name, entity.name)
        assertEquals(carMake.id, entity.id)
        assertEquals(year, entity.year)
    }

    @Test
    fun `CarMakeEntity toCarMake creates correct CarMake`() {
        // Given
        val entity = CarMakeEntity(name = "Honda", id = 2, year = 2021)

        // When
        val carMake = entity.toCarMake()

        // Then
        assertEquals(entity.name, carMake.name)
        assertEquals(entity.id, carMake.id)
    }

    @Test
    fun `CarMake toEntity and toCarMake are inverses`() {
        // Given
        val carMake = CarMake(id = 10, name = "Ford")
        val year = 2022

        // When
        val entity = carMake.toEntity(year)
        val result = entity.toCarMake()

        // Then
        assertEquals(carMake.id, result.id)
        assertEquals(carMake.name, result.name)
    }

    // ==================== CarModel mappers ====================

    @Test
    fun `CarModel toEntity creates correct CarModelEntity`() {
        // Given
        val carModel = CarModel(id = 1, makeId = 10, name = "Mustang")
        val year = 2020

        // When
        val entity = carModel.toEntity(year)

        // Then
        assertEquals(carModel.name, entity.name)
        assertEquals(carModel.id, entity.id)
        assertEquals(year, entity.year)
        assertEquals(carModel.makeId, entity.makeId)
    }

    @Test
    fun `CarModelEntity toCarModel creates correct CarModel`() {
        // Given
        val entity = CarModelEntity(name = "Camry", id = 5, year = 2021, makeId = 3)

        // When
        val carModel = entity.toCarModel()

        // Then
        assertEquals(entity.name, carModel.name)
        assertEquals(entity.id, carModel.id)
        assertEquals(entity.makeId, carModel.makeId)
    }

    @Test
    fun `CarModel toEntity and toCarModel are inverses`() {
        // Given
        val carModel = CarModel(id = 7, makeId = 12, name = "Explorer")
        val year = 2023

        // When
        val entity = carModel.toEntity(year)
        val result = entity.toCarModel()

        // Then
        assertEquals(carModel.id, result.id)
        assertEquals(carModel.makeId, result.makeId)
        assertEquals(carModel.name, result.name)
    }

    // ==================== CarTrim mappers ====================

    @Test
    fun `CarTrim toEntity creates correct CarTrimEntity`() {
        // Given
        val carTrim = CarTrim(
            id = 100,
            name = "LX",
            description = "Luxury Edition",
            msrp = 35000,
            invoice = 33000,
            modelId = 10,
            year = 2023,
            created = "2023-01-01T00:00:00Z",
            modified = "2023-01-02T00:00:00Z"
        )
        val year = 2023

        // When
        val entity = carTrim.toEntity(year)

        // Then
        assertEquals(carTrim.id, entity.id)
        assertEquals(carTrim.name, entity.name)
        assertEquals(year, entity.year)
        assertEquals(carTrim.modelId, entity.modelId)
        assertEquals(carTrim.msrp, entity.msrp)
        assertEquals(carTrim.invoice, entity.invoice)
        assertEquals(carTrim.description, entity.description)
        assertEquals(carTrim.created, entity.created)
        assertEquals(carTrim.modified, entity.modified)
    }

    @Test
    fun `CarTrimEntity toCarTrim creates correct CarTrim`() {
        // Given
        val entity = CarTrimEntity(
            created = "2023-01-01T00:00:00Z",
            description = "Sport",
            invoice = 30000,
            modelId = 5,
            modified = "2023-01-02T00:00:00Z",
            msrp = 32000,
            name = "Sport Trim",
            id = 50,
            year = 2022
        )

        // When
        val carTrim = entity.toCarTrim()

        // Then
        assertEquals(entity.id, carTrim.id)
        assertEquals(entity.name, carTrim.name)
        assertEquals(entity.year, carTrim.year)
        assertEquals(entity.modelId, carTrim.modelId)
        assertEquals(entity.msrp, carTrim.msrp)
        assertEquals(entity.invoice, carTrim.invoice)
        assertEquals(entity.description, carTrim.description)
        assertEquals(entity.created, carTrim.created)
        assertEquals(entity.modified, carTrim.modified)
    }

    @Test
    fun `CarTrim toEntity and toCarTrim are inverses`() {
        // Given
        val carTrim = CarTrim(
            id = 200,
            name = "XLT",
            description = "XLT Trim",
            msrp = 40000,
            invoice = 38000,
            modelId = 15,
            year = 2024,
            created = "2024-01-01T00:00:00Z",
            modified = "2024-01-02T00:00:00Z"
        )
        val year = 2024

        // When
        val entity = carTrim.toEntity(year)
        val result = entity.toCarTrim()

        // Then
        assertEquals(carTrim.id, result.id)
        assertEquals(carTrim.name, result.name)
        assertEquals(carTrim.year, result.year)
        assertEquals(carTrim.modelId, result.modelId)
        assertEquals(carTrim.msrp, result.msrp)
        assertEquals(carTrim.invoice, result.invoice)
        assertEquals(carTrim.description, result.description)
        assertEquals(carTrim.created, result.created)
        assertEquals(carTrim.modified, result.modified)
    }

    // ==================== CarTrimDetail mappers ====================

    @Test
    fun `CarTrimDetail toEntity creates correct CarTrimDetailEntity`() {
        // Given
        val makeModel = MakeModel(id = 1, make = Make(id = 1, name = "Ford"), makeId = 1, name = "F-150")
        val trimBody = TrimBody(
            cargoCapacity = "52.8", curbWeight = 5500, doors = 4, frontTrack = "69.7",
            grossWeight = "7050", groundClearance = "8.4", height = "77.7", id = 1,
            length = "231.9", maxCargoCapacity = "52.8", maxPayload = null,
            maxTowingCapacity = "13200", rearTrack = "69.1", seats = 5, type = "SUV",
            wheelBase = "145", width = "79.9", trimId = 1
        )
        val trimEngine = TrimEngine(
            camType = "DOHC", cylinders = "6", driveType = "RWD", engineType = "V6",
            fuelType = "Regular", horsepowerHp = 300, horsepowerRpm = 6000, id = 1,
            size = "3.5L", torquePoundFoot = 270, torqueRpm = 4000,
            transmission = "Automatic", valveTiming = "Variable", valves = 24, trimId = 1
        )
        val trimMileage = TrimMileage(
            batteryCapacityElectric = null, combineMpg = 22, cityMpg = 19,
            cityMpgElectric = null, combinedMpgElectric = null, highwayMpg = 27,
            highwayMpgElectric = null, kwh100MiElectric = null, timeToCharge = null,
            fuelTankCapacity = "26.5", id = 1, rangeCity = 400, rangeElectric = null,
            rangeHighway = 540, trimId = 1
        )
        val exteriorColors = listOf(
            TrimColor(id = 1, trimId = 1, name = "White", rgb = "255,255,255"),
            TrimColor(id = 2, trimId = 1, name = "Black", rgb = "0,0,0")
        )
        val interiorColors = listOf(
            TrimColor(id = 3, trimId = 1, name = "Gray", rgb = "128,128,128")
        )
        val carTrimDetail = CarTrimDetail(
            id = 100,
            modelId = 10,
            year = 2023,
            name = "XLT",
            description = "XLT Trim Detail",
            msrp = 40000,
            invoice = 38000,
            trimInteriorColors = interiorColors,
            trimExteriorColors = exteriorColors,
            trimMileage = trimMileage,
            trimEngine = trimEngine,
            trimBody = trimBody,
            created = "2023-01-01T00:00:00Z",
            makeModel = makeModel,
            modified = "2023-01-02T00:00:00Z"
        )
        val year = 2023

        // When
        val entity = carTrimDetail.toEntity(year)

        // Then
        assertEquals(carTrimDetail.id, entity.id)
        assertEquals(year, entity.year)
        assertEquals(carTrimDetail.name, entity.name)
        assertEquals(carTrimDetail.modelId, entity.modelId)
        assertEquals(carTrimDetail.msrp, entity.msrp)
        assertEquals(carTrimDetail.invoice, entity.invoice)
        assertEquals(carTrimDetail.description, entity.description)
        assertEquals(carTrimDetail.created, entity.created)
        assertEquals(carTrimDetail.modified, entity.modified)
        assertEquals(carTrimDetail.makeModel, entity.makeModel)
        assertEquals(carTrimDetail.trimBody, entity.trimBody)
        assertEquals(carTrimDetail.trimEngine, entity.trimEngine)
        assertEquals(carTrimDetail.trimExteriorColors, entity.trimExteriorColors)
        assertEquals(carTrimDetail.trimInteriorColors, entity.trimInteriorColors)
        assertEquals(carTrimDetail.trimMileage, entity.trimMileage)
    }

    @Test
    fun `CarTrimDetailEntity toCarTrimDetail creates correct CarTrimDetail`() {
        // Given
        val entity = CarTrimDetailEntity(
            created = "2023-01-01T00:00:00Z",
            description = "Test Description",
            invoice = 35000,
            modelId = 10,
            modified = "2023-01-02T00:00:00Z",
            msrp = 37000,
            name = "Test Trim",
            id = 100,
            year = 2023,
            makeModel = null,
            trimBody = null,
            trimEngine = null,
            trimExteriorColors = null,
            trimInteriorColors = null,
            trimMileage = null
        )

        // When
        val carTrimDetail = entity.toCarTrimDetail()

        // Then
        assertEquals(entity.id, carTrimDetail.id)
        assertEquals(entity.year, carTrimDetail.year)
        assertEquals(entity.name, carTrimDetail.name)
        assertEquals(entity.modelId, carTrimDetail.modelId)
        assertEquals(entity.msrp, carTrimDetail.msrp)
        assertEquals(entity.invoice, carTrimDetail.invoice)
        assertEquals(entity.description, carTrimDetail.description)
        assertEquals(entity.created, carTrimDetail.created)
        assertEquals(entity.modified, carTrimDetail.modified)
        assertEquals(entity.makeModel, carTrimDetail.makeModel)
        assertEquals(entity.trimBody, carTrimDetail.trimBody)
        assertEquals(entity.trimEngine, carTrimDetail.trimEngine)
        assertEquals(emptyList<TrimColor>(), carTrimDetail.trimExteriorColors)
        assertEquals(emptyList<TrimColor>(), carTrimDetail.trimInteriorColors)
        assertEquals(entity.trimMileage, carTrimDetail.trimMileage)
    }

    @Test
    fun `CarTrimDetail toEntity and toCarTrimDetail are inverses with nullable fields`() {
        // Given
        val carTrimDetail = CarTrimDetail(
            id = 200,
            modelId = 20,
            year = 2024,
            name = "Lariat",
            description = "Lariat Trim",
            msrp = 50000,
            invoice = 47000,
            trimInteriorColors = emptyList(),
            trimExteriorColors = emptyList(),
            trimMileage = null,
            trimEngine = null,
            trimBody = null,
            created = "2024-01-01T00:00:00Z",
            makeModel = null,
            modified = "2024-01-02T00:00:00Z"
        )
        val year = 2024

        // When
        val entity = carTrimDetail.toEntity(year)
        val result = entity.toCarTrimDetail()

        // Then
        assertEquals(carTrimDetail.id, result.id)
        assertEquals(carTrimDetail.year, result.year)
        assertEquals(carTrimDetail.name, result.name)
        assertEquals(carTrimDetail.modelId, result.modelId)
        assertEquals(carTrimDetail.msrp, result.msrp)
        assertEquals(carTrimDetail.invoice, result.invoice)
        assertEquals(carTrimDetail.description, result.description)
        assertEquals(carTrimDetail.created, result.created)
        assertEquals(carTrimDetail.modified, result.modified)
        assertEquals(carTrimDetail.makeModel, result.makeModel)
        assertEquals(carTrimDetail.trimBody, result.trimBody)
        assertEquals(carTrimDetail.trimEngine, result.trimEngine)
        assertEquals(carTrimDetail.trimExteriorColors, result.trimExteriorColors)
        assertEquals(carTrimDetail.trimInteriorColors, result.trimInteriorColors)
        assertEquals(carTrimDetail.trimMileage, result.trimMileage)
    }

    @Test
    fun `CarTrimDetailEntity toCarTrimDetail handles null color lists`() {
        // Given
        val entity = CarTrimDetailEntity(
            created = "2023-01-01T00:00:00Z",
            description = "Test",
            invoice = 30000,
            modelId = 5,
            modified = "2023-01-02T00:00:00Z",
            msrp = 32000,
            name = "Base",
            id = 50,
            year = 2022,
            makeModel = null,
            trimBody = null,
            trimEngine = null,
            trimExteriorColors = null,
            trimInteriorColors = null,
            trimMileage = null
        )

        // When
        val carTrimDetail = entity.toCarTrimDetail()

        // Then
        assertEquals(emptyList<TrimColor>(), carTrimDetail.trimExteriorColors)
        assertEquals(emptyList<TrimColor>(), carTrimDetail.trimInteriorColors)
    }
}
