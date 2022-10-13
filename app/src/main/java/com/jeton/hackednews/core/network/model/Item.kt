package com.jeton.hackednews.core.network.model

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.ToJson

enum class Type(val kind: String) {
    STORY("story"),
    COMMENT("comment"),
    JOB("job"),
    POLL("poll"),
    POLLOPT("pollopt"),
    UNKNOWN("unknown");

    companion object {
        fun fromKind(kind: String) = values().associateBy(Type::kind)[kind] ?: UNKNOWN
    }
}

data class Item(
    @Json(name = "by") val by: String,
    @Json(name = "id") val id: Double,
    @Json(name = "descendants") val descendants: Int?,
    @Json(name = "kinds") val kids: List<Int>?,
    @Json(name = "parts") val parts: List<Int>?,
    @Json(name = "score") val score: Int?,
    @Json(name = "text") val text: String?,
    @Json(name = "time") val time: Double,
    @Json(name = "title") val title: String,
    @Json(name = "type") val type: Type,
    @Json(name = "url") val url: String,
)

class TypeAdapter {
    @ToJson
    fun toJson(type: Type): String = type.kind

    @FromJson
    fun fromJson(kind: String): Type = Type.fromKind(kind)
}