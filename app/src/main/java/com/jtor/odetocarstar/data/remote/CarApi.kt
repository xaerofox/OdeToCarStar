package com.jtor.odetocarstar.data.remote

import com.jtor.odetocarstar.data.models.CarApiCollection
import com.jtor.odetocarstar.data.models.CarMake
import retrofit2.http.GET

interface CarApi {

    @GET("/api/makes")
    suspend fun getMakes() : CarApiCollection<CarMake>
}