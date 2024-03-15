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
    var isLastPage = false
    var isFetching = false
    private var initialLoad = true  // Track if we're loading the first page

    fun fetchProducts() {
        if (isFetching || isLastPage) return

        isFetching = true
        viewModelScope.launch {
            when (val result = repository.getProducts(currentPage)) {
                is Result.Success -> {
                    if (result.data.isNotEmpty()) {
                        val currentProducts = (productList.value as? Result.Success)?.data ?: emptyList()
                        productList.postValue(Result.Success(currentProducts + result.data))
                        currentPage++
                        initialLoad = false
                    } else if (initialLoad) {
                        // If it's the first load and no data is returned, consider it as empty
                        productList.postValue(Result.Empty)
                        isLastPage = true
                    }
                    // If no data on subsequent pages, just mark it as the last page
                    if (result.data.isEmpty()) isLastPage = true
                }
                is Result.Error -> {
                    if (initialLoad) {
                        // Only propagate the error if this is the initial load
                        productList.postValue(result)
                    }
                    // Do not update isLastPage or currentPage - allow retries
                }
                is Result.Empty -> {
                    // Similar handling for empty results as for success with no data
                    if (initialLoad) productList.postValue(Result.Empty)
                    isLastPage = true
                }
            }
            isFetching = false
        }
    }

    fun loadMoreProducts() {
        fetchProducts()
    }
}