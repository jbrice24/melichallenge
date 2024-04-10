package com.jonathanbriceno.melichallenge.data.remote.model

data class Result(
    val attributes: List<Attribute?>?,
    val id: String?,
    val name: String?,
    val pictures: List<Picture?>?,
    val settings: Settings?,
    val status: String?
)