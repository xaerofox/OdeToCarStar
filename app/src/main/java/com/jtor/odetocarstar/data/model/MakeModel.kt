package com.jtor.odetocarstar.data.model

import com.squareup.moshi.Json

data class MakeModel(
    val id: Int,
    val make: Make,
    @Json(name = "make_id")
    val makeId: Int,
    val name: String
)