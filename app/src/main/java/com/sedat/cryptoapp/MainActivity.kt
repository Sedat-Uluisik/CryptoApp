package com.sedat.cryptoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sedat.cryptoapp.ui.theme.CryptoAppTheme
import com.sedat.cryptoapp.view.CryptoDetailScreen
import com.sedat.cryptoapp.view.CryptoListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoAppTheme {
                NavControllerApp()
            }
        }
    }
}

@Composable
fun NavControllerApp() { 
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "CryptoListScreen") {
        composable(route = "CryptoListScreen") {
            CryptoListScreen(navController = navController)
        }

        composable(
            route = "CryptoDetailScreen/{id}/{price}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            }, navArgument("price"){
                type = NavType.StringType
            }
            )
        ) {
            val id = remember {
                it.arguments?.getString("id") ?: ""
            }
            val price = remember {
                it.arguments?.getString("price") ?: ""
            }

            CryptoDetailScreen(id = id, price = price, navController = navController)
        }
    }
}