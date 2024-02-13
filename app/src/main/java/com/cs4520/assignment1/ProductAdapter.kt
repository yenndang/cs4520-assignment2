package com.cs4520.assignment1

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter for RecyclerView of ProductListFragment
class ProductAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun getItemCount(): Int = products.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productImage: ImageView = itemView.findViewById(R.id.imageViewProductIcon)
        private val productName: TextView = itemView.findViewById(R.id.textViewName)
        private val productPrice: TextView = itemView.findViewById(R.id.textViewPrice)
        private val productExpiryDate: TextView = itemView.findViewById(R.id.textViewExpiryDate)

        fun bind(product: Product) {
            productName.text = product.name
            productPrice.text = "$${product.price}"

            when (product) {
                is Product.Food -> {
                    productExpiryDate.visibility = if (product.expiryDate != null) View.VISIBLE else View.GONE
                    productExpiryDate.text = product.expiryDate ?: ""
                    itemView.setBackgroundColor(Color.parseColor("#FFD965")) // Light Yellow
                    productImage.setImageResource(R.drawable.food)
                }
                is Product.Equipment -> {
                    productExpiryDate.visibility = View.GONE
                    itemView.setBackgroundColor(Color.parseColor("#E06666")) // Light Red
                    productImage.setImageResource(R.drawable.equipment)
                }
            }
        }


    }
}
