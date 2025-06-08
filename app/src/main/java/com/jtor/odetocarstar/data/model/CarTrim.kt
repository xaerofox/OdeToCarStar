package com.jtor.odetocarstar.data.model

import com.squareup.moshi.Json

data class CarTrim(
    val created: String,
    val description: String,
    val id: Int,
    val invoice: Int,
    @Json(name = "make_model_id")
    val modelId: Int,
    val modified: String,
    val msrp: Int,
    val name: String,
    val year: Int
)