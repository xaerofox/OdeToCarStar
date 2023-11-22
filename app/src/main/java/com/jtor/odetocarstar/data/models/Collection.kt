package com.jtor.odetocarstar.data.models

data class Collection(
    val count: Int,
    val first: String,
    val last: String,
    val next: String,
    val pages: Int,
    val prev: String,
    val total: Int,
    val url: String
)