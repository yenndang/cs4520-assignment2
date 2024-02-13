package com.cs4520.assignment1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductListFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment and return the root view
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize the RecyclerView here
        recyclerView = view.findViewById(R.id.recyclerView)
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView = null
    }

    private fun setupRecyclerView() {
        // Check if recyclerView is not null before using it
        recyclerView?.let {
            productAdapter = ProductAdapter(productsDataset)
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = productAdapter
        }
    }
}


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        //find RecyclerView by id
//        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
//        val products = setUpProduct
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        val adapter = ProductAdapter(products)
//    }

