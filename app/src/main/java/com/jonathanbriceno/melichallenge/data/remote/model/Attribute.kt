package com.jonathanbriceno.melichallenge.data.remote.model

import com.google.gson.annotations.SerializedName

data class Attribute(
    val id: String?,
    val name: String?,
    @SerializedName("value_id") val valueId: String?,
    @SerializedName("value_name") val valueName: String?
)