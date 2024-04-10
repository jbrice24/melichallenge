package com.jonathanbriceno.melichallenge.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemotePayload(
    val keywords: String?,
    val paging: Paging?,
    @SerializedName("query_type") val queryType: String?,
    val results: List<Result?>?,
    @SerializedName("used_attributes") val usedAttributes: List<UsedAttribute?>?
)