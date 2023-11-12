package com.jtor.odetocarstar.data.remote

import retrofit2.http.GET

interface CarApi {

    @GET("/api/makes")
    suspend fun getMakes()
}