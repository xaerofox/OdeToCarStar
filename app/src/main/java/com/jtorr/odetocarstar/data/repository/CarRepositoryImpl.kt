package com.jtorr.odetocarstar.data.repository

import com.jtorr.odetocarstar.data.local.dao.CarMakeDao
import com.jtorr.odetocarstar.data.local.dao.CarModelDao
import com.jtorr.odetocarstar.data.local.dao.CarTrimDao
import com.jtorr.odetocarstar.data.local.dao.CarTrimDetailDao
import com.jtorr.odetocarstar.data.local.entity.CarMakeEntity
import com.jtorr.odetocarstar.data.local.entity.CarModelEntity
import com.jtorr.odetocarstar.data.local.entity.CarTrimDetailEntity
import com.jtorr.odetocarstar.data.local.entity.CarTrimEntity
import com.jtorr.odetocarstar.data.model.CarMake
import com.jtorr.odetocarstar.data.model.CarModel
import com.jtorr.odetocarstar.data.model.CarTrim
import com.jtorr.odetocarstar.data.model.CarTrimDetail
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val api: CarApi,
    private val carMakeDao: CarMakeDao,
    private val carModelDao: CarModelDao,
    private val carTrimDao: CarTrimDao,
    private val carTrimDetailDao: CarTrimDetailDao
) : CarRepository {
    override suspend fun getMakes(
        year: Int?,
        sort: String?
    ): List<CarMake> {
        val targetYear = year ?: 2015
        val cached = carMakeDao.getMakes(targetYear)
        if (cached.isNotEmpty()) {
            return cached.map { it.toCarMake() }
        }

        val makes = api.getMakes(
            year = targetYear,
            sort = sort
        ).data

        carMakeDao.insertMakes(makes.map { it.toEntity(targetYear) })

        return makes
    }

    override suspend fun getModels(year: Int, make: String): List<CarModel> {
        val makeId = findMakeIdByName(make, year)
        if (makeId != null) {
            val cached = carModelDao.getModels(year, makeId)
            if (cached.isNotEmpty()) {
                return cached.map { it.toCarModel() }
            }
        }

        val models = api.getModels(
            year = year,
            make = make
        ).data

        carModelDao.insertModels(models.map { it.toEntity(year) })

        return models
    }

    override suspend fun getTrims(year: Int, modelId: Int): List<CarTrim> {
        val cached = carTrimDao.getTrims(year, modelId)
        if (cached.isNotEmpty()) {
            return cached.map { it.toCarTrim() }
        }

        val trims = api.getTrims(year, modelId).data

        carTrimDao.insertTrims(trims.map { it.toEntity(year) })

        return trims
    }

    override suspend fun getTrimDetail(id: Int, year: Int): CarTrimDetail {
        val cached = carTrimDetailDao.getDetail(id, year)
        if (cached != null) {
            return cached.toCarTrimDetail()
        }

        val detail = api.getTrimDetail(id, year)

        carTrimDetailDao.insertDetail(detail.toEntity(year))

        return detail
    }

    private suspend fun findMakeIdByName(make: String, year: Int): Int? {
        return try {
            val makes = carMakeDao.getMakes(year)
            makes.firstOrNull { it.name.equals(make, ignoreCase = true) }?.id
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun clearCache() {
        carMakeDao.clearAll()
        carModelDao.clearAll()
        carTrimDao.clearAll()
        carTrimDetailDao.clearAll()
    }
}

fun CarMake.toEntity(year: Int) = CarMakeEntity(
    name = this.name,
    id = this.id,
    year = year
)

fun CarMakeEntity.toCarMake() = CarMake(
    id = this.id,
    name = this.name
)

fun CarModel.toEntity(year: Int) = CarModelEntity(
    name = this.name,
    id = this.id,
    year = year,
    makeId = this.makeId
)

fun CarModelEntity.toCarModel() = CarModel(
    id = this.id,
    makeId = this.makeId,
    name = this.name
)

fun CarTrim.toEntity(year: Int) = CarTrimEntity(
    id = this.id,
    name = this.name,
    year = year,
    modelId = this.modelId,
    msrp = this.msrp,
    invoice = this.invoice,
    description = this.description,
    created = this.created,
    modified = this.modified
)

fun CarTrimEntity.toCarTrim() = CarTrim(
    id = this.id,
    name = this.name,
    year = this.year,
    modelId = this.modelId,
    msrp = this.msrp,
    invoice = this.invoice,
    description = this.description,
    created = this.created,
    modified = this.modified
)

fun CarTrimDetail.toEntity(year: Int) = CarTrimDetailEntity(
    id = this.id,
    year = year,
    name = this.name,
    modelId = this.modelId,
    msrp = this.msrp,
    invoice = this.invoice,
    description = this.description,
    created = this.created,
    modified = this.modified,
    makeModel = this.makeModel,
    trimBody = this.trimBody,
    trimEngine = this.trimEngine,
    trimExteriorColors = this.trimExteriorColors,
    trimInteriorColors = this.trimInteriorColors,
    trimMileage = this.trimMileage
)

fun CarTrimDetailEntity.toCarTrimDetail() = CarTrimDetail(
    id = this.id,
    year = this.year,
    name = this.name,
    modelId = this.modelId,
    msrp = this.msrp,
    invoice = this.invoice,
    description = this.description,
    created = this.created,
    modified = this.modified,
    makeModel = this.makeModel,
    trimBody = this.trimBody,
    trimEngine = this.trimEngine,
    trimExteriorColors = this.trimExteriorColors ?: emptyList(),
    trimInteriorColors = this.trimInteriorColors ?: emptyList(),
    trimMileage = this.trimMileage
)
