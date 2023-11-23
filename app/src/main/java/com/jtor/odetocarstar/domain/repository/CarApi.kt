package com.jtor.odetocarstar.domain.repository

import com.jtor.odetocarstar.data.remote.dto.CollectionDto
import com.jtor.odetocarstar.domain.model.CarMake
import retrofit2.http.GET
import retrofit2.http.Query

interface CarApi {

    @GET("/api/makes")
    suspend fun getMakes(
        @Query("year") year: Int = 2015,
        @Query("sort") sort: String? = "name"
    ) : CollectionDto<CarMake>
}