package com.jonathanbriceno.melichallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonathanbriceno.melichallenge.domain.GetProductsBySearch
import com.jonathanbriceno.melichallenge.domain.model.ProductDomain
import com.jonathanbriceno.melichallenge.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val repository: Repository,
    private val useCase : GetProductsBySearch
) : ViewModel() {

    private val _products = MutableLiveData<List<ProductDomain>>()
    val products: LiveData<List<ProductDomain>> = _products

    fun getProductsBySearch(query: String) {
        useCase.execute(query)
            .onEach { _products.value = it }
            .catch { println(it) }
            .launchIn(viewModelScope)
    }

}