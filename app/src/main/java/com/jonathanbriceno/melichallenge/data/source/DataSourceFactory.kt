package com.jonathanbriceno.melichallenge.data.source

import com.jonathanbriceno.melichallenge.data.remote.RemoteImpl
import com.jonathanbriceno.melichallenge.data.remote.retrofit.ProductApi
import com.jonathanbriceno.melichallenge.data.repository.Remote
import javax.inject.Inject

class DataSourceFactory @Inject constructor(private val productApi: ProductApi) {
    private val remote: Remote = RemoteImpl(productApi)

    fun getRemote() = remote
}