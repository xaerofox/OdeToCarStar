package com.jtor.odetocarstar.data.model

import com.squareup.moshi.Json

data class TrimEngine(
    @Json(name = "cam_type")
    val camType: String?,
    val cylinders: String?,
    @Json(name = "drive_type")
    val driveType: String?,
    @Json(name = "engine_type")
    val engineType: String?,
    @Json(name = "fuel_type")
    val fuelType: String?,
    @Json(name = "horsepower_hp")
    val horsepowerHp: Int?,
    @Json(name = "horsepower_rpm")
    val horsepowerRpm: Int?,
    val id: Int?,
    @Json(name = "make_model_trim_id")
    val trimId: Int?,
    val size: String?,
    @Json(name = "torque_ft_lbs")
    val torquePoundFoot: Int?,
    @Json(name = "torque_rpm")
    val torqueRpm: Int?,
    val transmission: String?,
    @Json(name = "valve_timing")
    val valveTiming: String?,
    val valves: Int?
)