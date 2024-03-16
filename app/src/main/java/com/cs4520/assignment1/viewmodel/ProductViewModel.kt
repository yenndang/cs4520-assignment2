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
    private var currentPage = 1
    var isFetching = false

    fun fetchProducts() {
        if (isFetching) return

        isFetching = true
        viewModelScope.launch {
            when (val result = repository.getProducts(currentPage)) {
                is Result.Success -> {
                    val currentProducts = (productList.value as? Result.Success)?.data ?: emptyList()
                    productList.postValue(Result.Success(currentProducts + result.data))
                    currentPage++
                }
                is Result.Error -> {
                    productList.postValue(result)
                }
                is Result.Empty -> {
                    productList.postValue(Result.Empty)
                }
            }
            isFetching = false
        }
    }

    fun loadMoreProducts() {
        fetchProducts()
    }
}
