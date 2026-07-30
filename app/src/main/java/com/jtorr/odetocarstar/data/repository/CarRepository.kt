package com.jtorr.odetocarstar.data.repository

import com.jtorr.odetocarstar.data.model.CarMake
import com.jtorr.odetocarstar.data.model.CarModel
import com.jtorr.odetocarstar.data.model.CarTrim
import com.jtorr.odetocarstar.data.model.CarTrimDetail

interface CarRepository {
    suspend fun getMakes(year: Int?, sort: String?) : List<CarMake>

    suspend fun getModels(year: Int, make: String) : List<CarModel>

    suspend fun getTrims(year: Int, modelId: Int) : List<CarTrim>

    suspend fun getTrimDetail(id: Int, year: Int) : CarTrimDetail

    suspend fun clearCache()
}
