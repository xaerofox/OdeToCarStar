package com.jtor.odetocarstar.domain.model

data class MakeModelTrimMileage(
    val battery_capacity_electric: Any,
    val combined_mpg: Int,
    val epa_city_mpg: Int,
    val epa_city_mpg_electric: Any,
    val epa_combined_mpg_electric: Any,
    val epa_highway_mpg: Int,
    val epa_highway_mpg_electric: Any,
    val epa_kwh_100_mi_electric: Any,
    val epa_time_to_charge_hr_240v_electric: Any,
    val fuel_tank_capacity: String,
    val id: Int,
    val make_model_trim_id: Int,
    val range_city: Int,
    val range_electric: Any,
    val range_highway: Int
)