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

    // Initialize productAdapter with an empty list upfront
    private val productAdapter: ProductAdapter by lazy {
        ProductAdapter(mutableListOf())
    }

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
        setupRecyclerView()
        observeProducts()
        viewModel.fetchProducts()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }
    }

    private fun observeProducts() {
        viewModel.productList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    if (result.data.isEmpty()) {
                        binding.textViewEmpty.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    } else {
                        binding.textViewEmpty.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        productAdapter.updateData(result.data)
                    }
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.textViewEmpty.visibility = View.VISIBLE
                    binding.textViewEmpty.text = result.exception.message ?: "An error occurred"
                    binding.recyclerView.visibility = View.GONE
                }
                is Result.Empty -> {
                    binding.progressBar.visibility = View.GONE
                    binding.textViewEmpty.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
