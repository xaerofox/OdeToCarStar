package com.jtor.odetocarstar.data.repository

import com.jtor.odetocarstar.data.remote.dto.CollectionDto
import com.jtor.odetocarstar.data.model.CarMake
import com.jtor.odetocarstar.data.model.CarModel
import com.jtor.odetocarstar.data.model.CarTrim
import com.jtor.odetocarstar.data.model.CarTrimDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CarApi {

    @GET("/api/makes")
    suspend fun getMakes(
        @Query("year") year: Int = 2015,
        @Query("sort") sort: String? = "name"
    ): CollectionDto<CarMake>

    @GET("/api/models")
    suspend fun getModels(
        @Query("year") year: Int,
        @Query("make") make: String
    ): CollectionDto<CarModel>

    @GET("/api/trims")
    suspend fun getTrims(
        @Query("year") year: Int,
        @Query("make_model_id") modelId: Int
    ): CollectionDto<CarTrim>

    @GET("/api/trims/{id}")
    suspend fun getTrimDetail(@Path("id") trimId: Int): CarTrimDetail
}