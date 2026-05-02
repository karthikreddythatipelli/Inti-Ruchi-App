package com.intiruchi.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.intiruchi.app.ui.theme.IntiRuchiTheme

data class MenuItem(val id: String, val name: String, val price: Int, val type: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntiRuchiTheme {
                FoodSubscriptionApp()
            }
        }
    }
}

@Composable
fun FoodSubscriptionApp() {
    val foods = listOf(
        MenuItem("f1", "Veg Thali", 120, "Food"),
        MenuItem("f2", "Paneer Curry Meal", 150, "Food"),
        MenuItem("f3", "South Indian Combo", 130, "Food"),
        MenuItem("f4", "Millet Bowl", 160, "Food")
    )
    val subscriptions = listOf(
        MenuItem("s1", "15 Days Plan", 1800, "Subscription"),
        MenuItem("s2", "30 Days Plan", 3400, "Subscription")
    )

    val cart = remember { mutableStateListOf<MenuItem>() }
    var orderStatus by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { Text("Inti Ruchi", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold) }
            item { Text("Food Varieties", style = MaterialTheme.typography.titleLarge) }
            items(foods) { item -> ItemCard(item) { cart.add(item); orderStatus = "" } }

            item { Text("Subscription Plans", style = MaterialTheme.typography.titleLarge) }
            items(subscriptions) { item -> ItemCard(item) { cart.add(item); orderStatus = "" } }

            item {
                Text("Cart", style = MaterialTheme.typography.titleLarge)
                if (cart.isEmpty()) {
                    Text("Your cart is empty")
                } else {
                    cart.forEach { Text("• ${it.name} - ₹${it.price}") }
                }
                val total = cart.sumOf { it.price }
                Text("Total: ₹$total", fontWeight = FontWeight.Bold)
                Button(onClick = {
                    orderStatus = if (cart.isEmpty()) {
                        "Please add items before placing order"
                    } else {
                        cart.clear()
                        "Order placed successfully"
                    }
                }) {
                    Text("Place Order")
                }
                if (orderStatus.isNotBlank()) {
                    Text(orderStatus)
                }
            }
        }
    }
}

@Composable
fun ItemCard(item: MenuItem, onAdd: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(item.name, fontWeight = FontWeight.SemiBold)
                Text("₹${item.price}")
            }
            Button(onClick = onAdd) {
                Text("Add")
            }
        }
    }
}
