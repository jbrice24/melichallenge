package com.jonathanbriceno.melichallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jonathanbriceno.melichallenge.presentation.SearchViewModel
import com.jonathanbriceno.melichallenge.ui.components.pages.SearchPage
import com.jonathanbriceno.melichallenge.ui.theme.MeliChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val products by viewModel.products.observeAsState()
            val loading by viewModel.loading.observeAsState()
            MeliChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    SearchPage(products = products.orEmpty(), loading = loading) {
                        executeSearch(it)
                    }
                }
            }
        }
    }

    private fun executeSearch(query: String) {
        viewModel.getProductsBySearch(query)
    }
}