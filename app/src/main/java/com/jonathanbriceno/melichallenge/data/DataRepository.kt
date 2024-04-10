package com.jonathanbriceno.melichallenge.data

import com.jonathanbriceno.melichallenge.data.remote.retrofit.ProductApi
import com.jonathanbriceno.melichallenge.data.source.DataSourceFactory
import com.jonathanbriceno.melichallenge.domain.model.ProductDomain
import com.jonathanbriceno.melichallenge.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onErrorResume
import javax.inject.Inject

class DataRepository @Inject constructor(private val dataSourceFactory: DataSourceFactory): Repository {
    override fun getProducts(query: String): Flow<List<ProductDomain>?> =
        flow {
            emit(dataSourceFactory.getRemote().getProducts(query))
        }.map { response ->
            response.results?.map { product ->
                ProductDomain(
                    name = product?.name.orEmpty(),
                    image = product?.pictures?.firstOrNull()?.url.orEmpty(),
                    line = product?.attributes?.firstOrNull { it?.id == "LINE" }?.valueName.orEmpty(),
                    brand = product?.attributes?.firstOrNull { it?.id == "BRAND" }?.valueName.orEmpty(),
                    color = product?.attributes?.firstOrNull { it?.id == "COLOR" }?.valueName.orEmpty(),
                )
            }
        }
}