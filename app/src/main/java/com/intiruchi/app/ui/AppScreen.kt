package com.intiruchi.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.intiruchi.app.data.SampleData
import com.intiruchi.app.model.MenuItem
import com.intiruchi.app.viewmodel.AppViewModel

@Composable
fun AppScreen(viewModel: AppViewModel) {
    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { Text("Inti Ruchi", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold) }
        item { Text("Food Varieties", style = MaterialTheme.typography.titleLarge) }
        items(SampleData.foods) { item -> ProductCard(item = item, onAdd = { viewModel.addToCart(item) }) }

        item { Text("Subscriptions", style = MaterialTheme.typography.titleLarge) }
        items(SampleData.subscriptions) { item -> ProductCard(item = item, onAdd = { viewModel.addToCart(item) }) }

        item {
            Text("Cart", style = MaterialTheme.typography.titleLarge)
            if (viewModel.cartItems.isEmpty()) {
                Text("Your cart is empty")
            } else {
                viewModel.cartItems.forEach {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("${it.item.name} x${it.quantity}")
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(onClick = { viewModel.removeOne(it.item) }) { Text("-") }
                            Text("₹${it.item.price * it.quantity}")
                        }
                    }
                }
            }

            Text(
                text = "Total: ₹${viewModel.totalAmount()}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )

            Button(
                onClick = { viewModel.placeOrder() },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Place Order")
            }

            if (viewModel.orderMessage.isNotBlank()) {
                Text(viewModel.orderMessage, modifier = Modifier.padding(top = 8.dp))
            }
        }
    }
}

@Composable
private fun ProductCard(item: MenuItem, onAdd: () -> Unit) {
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
