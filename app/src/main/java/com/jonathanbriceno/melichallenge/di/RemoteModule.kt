package com.jonathanbriceno.melichallenge.di

import com.jonathanbriceno.melichallenge.data.DataRepository
import com.jonathanbriceno.melichallenge.data.remote.retrofit.ProductApi
import com.jonathanbriceno.melichallenge.data.source.DataSourceFactory
import com.jonathanbriceno.melichallenge.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    fun provideRetrofilClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    fun provideRepository(dataSourceFactory: DataSourceFactory) : Repository {
        return DataRepository(dataSourceFactory)
    }

    @Provides
    fun provideDataSource(api: ProductApi) : DataSourceFactory {
        return DataSourceFactory(api)
    }
}