package com.jonathanbriceno.melichallenge.data.repository

import com.jonathanbriceno.melichallenge.data.remote.model.RemotePayload
import kotlinx.coroutines.flow.Flow

interface Remote {
    suspend fun getProducts(query: String) : RemotePayload
}