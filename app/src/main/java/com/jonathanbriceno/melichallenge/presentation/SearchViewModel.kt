package com.jonathanbriceno.melichallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonathanbriceno.melichallenge.domain.GetProductsBySearch
import com.jonathanbriceno.melichallenge.domain.model.ProductDomain
import com.jonathanbriceno.melichallenge.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val repository: Repository,
    private val useCase: GetProductsBySearch
) : ViewModel() {

    private val _products = MutableLiveData<List<ProductDomain>>()
    val products: LiveData<List<ProductDomain>> = _products
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun getProductsBySearch(query: String) {
        _loading.value = true
        useCase.execute(query)
            .flowOn(Dispatchers.IO)
            .onEach {
                _loading.value = false
                _products.value = it
            }
            .catch {
                _loading.value = false
                println(it)
            }
            .launchIn(viewModelScope)
    }

}