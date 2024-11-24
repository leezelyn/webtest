package com.tse.webtest.model

import com.squareup.moshi.Json

data class Product(
    @Json(name = "_id") val id: String,
    val title: String,
    val description: String,
    val detail: String,
    val cover: String,
    val editTime: String,
    @Json(name = "__v") val version: Int
)