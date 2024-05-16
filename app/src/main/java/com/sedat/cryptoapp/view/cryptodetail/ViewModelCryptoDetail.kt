package com.sedat.cryptoapp.view.cryptodetail

import androidx.lifecycle.ViewModel
import com.sedat.cryptoapp.model.CryptoDetailItem
import com.sedat.cryptoapp.repo.CryptoRepo
import com.sedat.cryptoapp.ui.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelCryptoDetail @Inject constructor(
    private val repository: CryptoRepo
): ViewModel() {

    suspend fun getCryptoDetail(cryptoId: String): Resource<List<CryptoDetailItem>>{
        return repository.getCryptoDetail()
    }

}