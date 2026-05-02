package com.intiruchi.app.model

enum class ItemType { FOOD, SUBSCRIPTION }

data class MenuItem(
    val id: String,
    val name: String,
    val price: Int,
    val type: ItemType
)

data class CartItem(
    val item: MenuItem,
    val quantity: Int
)
