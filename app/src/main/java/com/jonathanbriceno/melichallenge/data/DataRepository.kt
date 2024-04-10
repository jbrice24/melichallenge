package com.jonathanbriceno.melichallenge.data

import com.jonathanbriceno.melichallenge.data.mapper.toDomain
import com.jonathanbriceno.melichallenge.data.source.DataSourceFactory
import com.jonathanbriceno.melichallenge.domain.model.ProductDomain
import com.jonathanbriceno.melichallenge.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val dataSourceFactory: DataSourceFactory
): Repository {
    override fun getProducts(query: String): Flow<List<ProductDomain>?> =
        flow {
            emit(dataSourceFactory.getRemote().getProducts(query))
        }.map { response ->
            response.results?.toDomain()
        }
}