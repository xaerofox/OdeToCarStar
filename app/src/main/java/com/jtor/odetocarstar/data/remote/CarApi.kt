package com.jtor.odetocarstar.data.remote

import com.jtor.odetocarstar.BuildConfig
import retrofit2.http.GET

interface CarApi {

    @GET("${BuildConfig.BASE_URL}/api/makes")
    suspend fun getMakes()
}