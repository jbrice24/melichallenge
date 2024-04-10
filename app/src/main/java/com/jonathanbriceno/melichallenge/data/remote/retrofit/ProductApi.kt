package com.jonathanbriceno.melichallenge.data.remote.retrofit

import com.jonathanbriceno.melichallenge.data.remote.model.RemotePayload
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    @GET("products/search")
    suspend fun searchProducts(
        @Query("status") status: String,
        @Query("site_id") siteId: String,
        @Query("q") query: String
    ): RemotePayload
}