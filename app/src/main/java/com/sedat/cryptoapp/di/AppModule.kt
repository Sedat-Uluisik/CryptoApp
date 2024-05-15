package com.sedat.cryptoapp.di

import com.sedat.cryptoapp.api.ApiService
import com.sedat.cryptoapp.repo.CryptoRepo
import com.sedat.cryptoapp.ui.util.Constants.BASEURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): ApiService{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASEURL)
            .build()
            .create(ApiService::class.java)
    }

    /*@Provides
    @Singleton
    fun provideRepository(apiService: ApiService) = CryptoRepo(apiService)*/
}