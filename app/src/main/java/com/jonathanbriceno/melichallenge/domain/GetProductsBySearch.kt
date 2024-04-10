package com.jonathanbriceno.melichallenge.domain

import com.jonathanbriceno.melichallenge.data.DataRepository
import com.jonathanbriceno.melichallenge.domain.model.ProductDomain
import com.jonathanbriceno.melichallenge.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsBySearch @Inject constructor(val repository: Repository) {

    fun execute(query: String) : Flow<List<ProductDomain>?> =
        repository.getProducts(query)


}