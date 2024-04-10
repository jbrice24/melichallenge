package com.jonathanbriceno.melichallenge.data.remote

import com.jonathanbriceno.melichallenge.data.remote.retrofit.ProductApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.mercadolibre.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ProductApi = retrofit.create(ProductApi::class.java)
}