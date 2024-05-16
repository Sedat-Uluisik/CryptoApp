package com.sedat.cryptoapp.view.cryptolist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedat.cryptoapp.model.CryptoItem
import com.sedat.cryptoapp.repo.CryptoRepo
import com.sedat.cryptoapp.ui.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelCryptoList @Inject constructor(
    private val repository: CryptoRepo
): ViewModel() {

    var cryptoList = mutableStateOf(listOf<CryptoItem>()) //api'den alınan ve search edilen liste burada tutulur
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var initialCryptoList = listOf<CryptoItem>() //api'den alınan orijinal liste burda tutulur.
    private var isSearching = true

    init {
        getCryptoList()
    }

    fun search(query: String){
        val listToSearch = if(isSearching){
            cryptoList.value
        }else{
            initialCryptoList
        }

        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()){
                cryptoList.value = initialCryptoList
                isSearching = true
                return@launch
            }

            val result = listToSearch.filter { it.currency.contains(query.trim(), ignoreCase = true) }

            if (isSearching){
                initialCryptoList = cryptoList.value
                isSearching = false
            }

            cryptoList.value = result
        }
    }

    fun getCryptoList() = viewModelScope.launch{
        isLoading.value = true
        val result = repository.getCryptoList()

        when(result){
            is Resource.Loading ->{

            }
            is Resource.Success ->{
                errorMessage.value = ""
                isLoading.value = false
                cryptoList.value = result.data ?: listOf()
            }
            is Resource.Error ->{
                errorMessage.value = result.message ?: "Hata!"
                isLoading.value = false
            }
        }
    }

}