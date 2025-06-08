package com.jtor.odetocarstar.data.repository

import com.jtor.odetocarstar.data.model.CarMake
import com.jtor.odetocarstar.data.model.CarModel
import com.jtor.odetocarstar.data.model.CarTrim
import com.jtor.odetocarstar.data.model.CarTrimDetail
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val api: CarApi
) : CarRepository {
    override suspend fun getMakes(
        year: Int?,
        sort: String?
    ) : List<CarMake> {
        return api.getMakes(
            year = year ?: 2015,
            sort = sort
        ).data
    }

    override suspend fun getModels(year: Int, make: String): List<CarModel> {
        return api.getModels(
            year = year,
            make = make
        ).data
    }

    override suspend fun getTrims(year: Int, modelId: Int): List<CarTrim> =
        api.getTrims(year, modelId).data

    override suspend fun getTrimDetail(id: Int): CarTrimDetail =
        api.getTrimDetail(id)

}