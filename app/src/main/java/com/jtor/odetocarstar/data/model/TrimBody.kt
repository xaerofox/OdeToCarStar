package com.jtor.odetocarstar.data.model

import com.squareup.moshi.Json

data class TrimBody(
    @Json(name = "cargo_capacity")
    val cargoCapacity: String?,
    @Json(name = "curb_weight")
    val curbWeight: Int?,
    val doors: Int?,
    @Json(name = "front_track")
    val frontTrack: String?,
    @Json(name = "gross_weight")
    val grossWeight: String?,
    @Json(name = "ground_clearance")
    val groundClearance: String?,
    val height: String?,
    val id: Int?,
    val length: String?,
    @Json(name = "make_model_trim_id")
    val trimId: Int?,
    @Json(name = "max_cargo_capacity")
    val maxCargoCapacity: String?,
    @Json(name = "max_payload")
    val maxPayload: String?,
    @Json(name = "max_towing_capacity")
    val maxTowingCapacity: String?,
    @Json(name = "rear_track")
    val rearTrack: String?,
    val seats: Int?,
    val type: String?,
    @Json(name = "wheel_base")
    val wheelBase: String?,
    val width: String?
)