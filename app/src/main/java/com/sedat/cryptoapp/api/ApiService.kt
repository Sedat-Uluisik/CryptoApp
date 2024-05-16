package com.sedat.cryptoapp.api

import com.sedat.cryptoapp.model.CryptoDetailItem
import com.sedat.cryptoapp.model.CryptoItem
import retrofit2.http.GET

interface ApiService {
    @GET("IA32-CryptoComposeData/main/cryptolist.json")
    suspend fun getCryptoList(): List<CryptoItem>

    @GET("IA32-CryptoComposeData/main/crypto.json")
    suspend fun getCryptoDetail(): List<CryptoDetailItem>
}