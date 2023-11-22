package com.jtor.odetocarstar.domain.repository

import com.jtor.odetocarstar.data.remote.dto.CollectionDto
import com.jtor.odetocarstar.domain.model.CarMake
import retrofit2.http.GET

interface CarApi {

    @GET("/api/makes")
    suspend fun getMakes() : CollectionDto<CarMake>
}