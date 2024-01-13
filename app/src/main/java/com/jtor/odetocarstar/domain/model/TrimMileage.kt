package com.jtor.odetocarstar.domain.model

import com.squareup.moshi.Json

data class TrimMileage(
    @Json(name = "battery_capacity_electric")
    val batteryCapacityElectric: String?,
    @Json(name = "combined_mpg")
    val combineMpg: Int?,
    @Json(name = "epa_city_mpg")
    val cityMpg: Int?,
    @Json(name = "epa_city_mpg_electric")
    val cityMpgElectric: Int?,
    @Json(name = "epa_combined_mpg_electric")
    val combinedMpgElectric: Int?,
    @Json(name = "epa_highway_mpg")
    val highwayMpg: Int?,
    @Json(name= "epa_highway_mpg_electric")
    val highwayMpgElectric: Int?,
    @Json(name= "epa_kwh_100_mi_electric")
    val kwh100MiElectric: Int?,
    @Json(name = "epa_time_to_charge_hr_240v_electric")
    val timeToCharge: Int?,
    @Json(name = "fuel_tank_capacity")
    val fuelTankCapacity: String?,
    val id: Int?,
    @Json(name = "make_model_trim_id")
    val trimId: Int?,
    @Json(name = "range_city")
    val rangeCity: Int?,
    @Json(name = "range_electric")
    val rangeElectric: Int?,
    @Json(name = "range_highway")
    val rangeHighway: Int?
)