package com.sedat.cryptoapp.view.cryptodetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.sedat.cryptoapp.model.CryptoDetailItem
import com.sedat.cryptoapp.model.CryptoItem
import com.sedat.cryptoapp.ui.util.Resource
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CryptoDetailScreen(
    id: String,
    price: String,
    navController: NavController,
    viewModelCryptoDetail: ViewModelCryptoDetail = hiltViewModel()
) {

    //------------------------- yöntem 1 (hatalı !!!), sürekli recomposition yapılıyor

    /*val scope = rememberCoroutineScope() //bu scope'u kullanıcı etkileşimi olan yerlerde coroutine ihtiyacı olursa orada kullan (butona tıklama gibi, onClick içinde)

    var cryptoItem by remember {
        mutableStateOf<Resource<CryptoDetailItem>>(Resource.Loading())
    }

    scope.launch {
        cryptoItem = viewModelCryptoDetail.getCryptoDetail(id)
    }*/

    //------------------------------- yöntem 2

    /*var cryptoItem by remember {
        mutableStateOf<Resource<List<CryptoDetailItem>>>(Resource.Loading())
    }

    LaunchedEffect(
        key1 = Unit //hangi durum değişirse recomposition yapılsın?
    ){
        cryptoItem = viewModelCryptoDetail.getCryptoDetail(id)
    }*/

    //-------------------------------- yöntem 3

    val cryptoItem by produceState<Resource<List<CryptoDetailItem>>>(initialValue = Resource.Loading()){
        value = viewModelCryptoDetail.getCryptoDetail(id)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary),
        contentAlignment = Alignment.Center,
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when(cryptoItem){
                is Resource.Loading -> {
                    CircularProgressIndicator(color = Color.Black)
                    println("loading")
                }
                is Resource.Success ->{
                    Text(
                        text = cryptoItem.data?.first()?.name ?: "---",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                    
                    Image(
                        painter = rememberImagePainter(data = cryptoItem.data?.first()?.logo_url),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(200.dp)
                            .clip(CircleShape)
                            .border(3.dp, Color.Black, CircleShape)
                    )
                    println("success")
                    Text(
                        text = price,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                is Resource.Error ->{
                    Text(text = "Data Alınamadı1", color = Color.Red)
                }
            }
        }
    }
}