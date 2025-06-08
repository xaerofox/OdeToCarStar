package com.jtor.odetocarstar.data.repository

import com.jtor.odetocarstar.data.model.CarMake
import com.jtor.odetocarstar.data.model.CarModel
import com.jtor.odetocarstar.data.model.CarTrim
import com.jtor.odetocarstar.data.model.CarTrimDetail

interface CarRepository {
    suspend fun getMakes(year: Int?, sort: String?) : List<CarMake>

    suspend fun getModels(year: Int, make: String) : List<CarModel>

    suspend fun getTrims(year: Int, modelId: Int) : List<CarTrim>

    suspend fun getTrimDetail(id: Int) : CarTrimDetail
}