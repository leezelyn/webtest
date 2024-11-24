package com.tse.webtest.utils

import com.tse.webtest.model.ProductResponse
import retrofit2.http.GET

interface ApiService {
    @GET("webapi/product/list")
    suspend fun getProductList(): ProductResponse
}

