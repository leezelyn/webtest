package com.tse.webtest.model

import com.squareup.moshi.Json

data class ProductResponse(
    @Json(name = "ActionType") val actionType: String,
    val data: List<Product>
)