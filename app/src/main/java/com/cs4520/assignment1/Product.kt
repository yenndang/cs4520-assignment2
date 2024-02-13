package com.cs4520.assignment1

sealed class Product {
    abstract val name: String
    abstract val price: Int

    data class Food(
        override val name: String,
        val expiryDate: String?,
        override val price: Int
    ) : Product()

    data class Equipment(
        override val name: String,
        override val price: Int
    ) : Product()
}
