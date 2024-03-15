package com.cs4520.assignment1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.cs4520.assignment1.models.Product
import com.cs4520.assignment1.repository.ProductRepository
import com.cs4520.assignment1.utils.Result

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
    val productList = MutableLiveData<Result<List<Product>>>()

    fun fetchProducts(page: Int? = null) {
        viewModelScope.launch {
            productList.value = repository.getProducts(page)
        }
    }
}
