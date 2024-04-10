package com.jonathanbriceno.melichallenge.data.remote

import com.jonathanbriceno.melichallenge.data.remote.model.RemotePayload
import com.jonathanbriceno.melichallenge.data.remote.retrofit.ProductApi
import com.jonathanbriceno.melichallenge.data.repository.Remote
import javax.inject.Inject

class RemoteImpl @Inject constructor(
    private val apiService: ProductApi
) : Remote {
    override suspend fun getProducts(query: String): RemotePayload =
        apiService.searchProducts(
            status = "active",
            siteId = "MLC",
            query = query
        )
}