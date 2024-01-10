package com.jtor.odetocarstar.domain.model

import com.squareup.moshi.Json

data class TrimExteriorColor(
    val id: Int,
    @Json(name = "make_model_trim_id")
    val trimId: Int,
    val name: String,
    val rgb: String
)