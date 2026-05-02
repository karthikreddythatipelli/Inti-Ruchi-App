package com.intiruchi.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.intiruchi.app.model.CartItem
import com.intiruchi.app.model.MenuItem

class AppViewModel : ViewModel() {
    var cartItems by mutableStateOf(listOf<CartItem>())
        private set

    var orderMessage by mutableStateOf("")
        private set

    fun addToCart(item: MenuItem) {
        orderMessage = ""
        val existing = cartItems.find { it.item.id == item.id }
        cartItems = if (existing == null) {
            cartItems + CartItem(item, 1)
        } else {
            cartItems.map {
                if (it.item.id == item.id) it.copy(quantity = it.quantity + 1) else it
            }
        }
    }

    fun removeOne(item: MenuItem) {
        val existing = cartItems.find { it.item.id == item.id } ?: return
        cartItems = if (existing.quantity == 1) {
            cartItems.filterNot { it.item.id == item.id }
        } else {
            cartItems.map {
                if (it.item.id == item.id) it.copy(quantity = it.quantity - 1) else it
            }
        }
    }

    fun placeOrder() {
        if (cartItems.isEmpty()) {
            orderMessage = "Please add at least one item."
            return
        }

        cartItems = emptyList()
        orderMessage = "Order placed successfully!"
    }

    fun totalAmount(): Int = cartItems.sumOf { it.item.price * it.quantity }
}
