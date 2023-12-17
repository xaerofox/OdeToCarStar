package com.jtor.odetocarstar.domain.model

import com.squareup.moshi.Json

data class CarTrimDetail(
    val created: String,
    val description: String,
    val id: Int,
    val invoice: Int,
    @Json(name = "make_model")
    val makeModel: MakeModel,
    @Json(name = "make_model_id")
    val modelId: Int,
    @Json(name = "make_model_trim_body")
    val trimBody: TrimBody,
    @Json(name = "make_model_trim_engine")
    val trimEngine: TrimEngine,
    @Json(name = "make_model_trim_exterior_colors")
    val trimExteriorColors: List<TrimExteriorColor>,
    @Json(name = "make_model_trim_interior_colors")
    val trimInteriorColors: List<TrimInteriorColor>,
    @Json(name = "make_model_trim_mileage")
    val trimMileage: TrimMilage,
    val modified: String,
    val msrp: Int,
    val name: String,
    val year: Int
)