package com.sedat.cryptoapp.repo

import com.sedat.cryptoapp.api.ApiService
import com.sedat.cryptoapp.model.CryptoDetailItem
import com.sedat.cryptoapp.model.CryptoItem
import com.sedat.cryptoapp.ui.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CryptoRepo @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getCryptoList(): Resource<List<CryptoItem>>{
        val response = try {
            apiService.getCryptoList()
        }catch (e: Exception){
            return Resource.Error(e.message.toString())
        }

        return Resource.Success(response)
    }

    suspend fun getCryptoDetail(): Resource<CryptoDetailItem>{
        val response = try {
            apiService.getCryptoDetail()
        }catch (e: Exception){
            return Resource.Error(e.message.toString())
        }

        return Resource.Success(response)
    }
}