package com.jtor.odetocarstar.data.model

import com.squareup.moshi.Json


data class CarModel(
    val id: Int,
    @Json(name = "make_id")
    val makeId: Int,
    val name: String
)