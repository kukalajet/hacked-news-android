package com.jeton.hackednews.core.network.model

import com.squareup.moshi.Json

data class User(
    @Json(name = "about") val about: String,
    @Json(name = "createdAt") val createdAt: Double,
    @Json(name = "delay") val delay: Int,
    @Json(name = "id") val id: String,
    @Json(name = "karma") val karma: Int,
    @Json(name = "submitted") val submitted: List<Int>
)
