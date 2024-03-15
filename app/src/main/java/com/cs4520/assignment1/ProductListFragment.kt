package com.cs4520.assignment1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cs4520.assignment1.api.AppDatabaseSingleton
import com.cs4520.assignment1.databinding.FragmentProductListBinding
import com.cs4520.assignment1.repository.ProductRepository
import com.cs4520.assignment1.viewmodel.ProductViewModel
import com.cs4520.assignment1.api.RetrofitInstance
import com.cs4520.assignment1.viewmodel.ProductViewModelFactory
import com.cs4520.assignment1.utils.Result

class ProductListFragment : Fragment() {
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    // Initialize productAdapter with an empty list
    private lateinit var productAdapter: ProductAdapter

    private val viewModel: ProductViewModel by viewModels {
        ProductViewModelFactory(
            ProductRepository(
                RetrofitInstance.api,
                AppDatabaseSingleton.getDatabase(requireContext()).productDao(),
                requireContext()
            )
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        observeProducts()
        viewModel.fetchProducts()
    }

    private fun observeProducts() {
        viewModel.productList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.textViewEmpty.visibility = View.GONE
                    productAdapter = ProductAdapter(result.data)
                    binding.recyclerView.adapter = productAdapter
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, result.exception.message ?: "An error occurred", Toast.LENGTH_LONG).show()
                }
                is Result.Empty -> {
                    binding.progressBar.visibility = View.GONE
                    binding.textViewEmpty.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
