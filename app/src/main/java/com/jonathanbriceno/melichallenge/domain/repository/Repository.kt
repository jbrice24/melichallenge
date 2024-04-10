package com.jonathanbriceno.melichallenge.domain.repository

import com.jonathanbriceno.melichallenge.domain.model.ProductDomain
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getProducts(query: String) : Flow<List<ProductDomain>?>
}