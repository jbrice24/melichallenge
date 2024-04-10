package com.jonathanbriceno.melichallenge.ui.components.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jonathanbriceno.melichallenge.R
import com.jonathanbriceno.melichallenge.domain.model.ProductDomain
import com.jonathanbriceno.melichallenge.ui.components.molecules.Pod

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(
    products: List<ProductDomain>,
    onSearch: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val searchHistory = remember { mutableStateListOf("") }
    var isError by remember { mutableStateOf(false) }

    val contentPadding = if (active) PaddingValues() else PaddingValues(16.dp)

    Column {
        SearchBar(
            modifier = Modifier.padding(contentPadding),
            colors = SearchBarDefaults.colors(
                dividerColor = Color.LightGray
            ),
            query = query,
            onQueryChange = { query = it },
            onSearch = {
                if(it.isNotEmpty()) {
                    isError = false
                    active = false
                    onSearch(it)
                    searchHistory.add(it)
                } else {
                    isError = true
                }
            },
            active = active,
            onActiveChange = { active = it },
            placeholder = {
                Text(text = stringResource(R.string.text_search_placeholder))
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.text_search_icon))
            },
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (query.isNotEmpty()) {
                                query = ""
                            } else {
                                active = false
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.text_close_icon)
                    )
                }
            }
        ) {
            searchHistory.forEach {
                if (it.isNotEmpty()) {
                    Row(modifier = Modifier
                        .padding(all = 14.dp)
                        .clickable {
                            active = false
                            query = it
                            onSearch(it)
                        }
                    ) {
                        Icon(imageVector = Icons.Default.History, contentDescription = null)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = it)
                    }
                }
            }
        }

        products.let {
            LazyColumn {
                items(it) { product ->
                    Pod(product)
                }
            }
        }
    }
}