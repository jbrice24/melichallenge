package com.jonathanbriceno.melichallenge.data.mapper

import com.jonathanbriceno.melichallenge.data.remote.model.RemotePayload
import com.jonathanbriceno.melichallenge.data.remote.model.Result
import com.jonathanbriceno.melichallenge.domain.model.ProductDomain

fun List<Result?>.toDomain() : List<ProductDomain> =
    mapNotNull { it?.toDomain() }
fun Result.toDomain() : ProductDomain =
    ProductDomain(
        name = name.orEmpty(),
        image = pictures?.firstOrNull()?.url.orEmpty(),
        line = attributes?.firstOrNull { it?.id == "LINE" }?.valueName.orEmpty(),
        brand = attributes?.firstOrNull { it?.id == "BRAND" }?.valueName.orEmpty(),
        color = attributes?.firstOrNull { it?.id == "COLOR" }?.valueName.orEmpty(),
    )