package com.intiruchi.app.data

import com.intiruchi.app.model.ItemType
import com.intiruchi.app.model.MenuItem

object SampleData {
    val foods = listOf(
        MenuItem("f1", "Veg Thali", 120, ItemType.FOOD),
        MenuItem("f2", "Paneer Curry Meal", 150, ItemType.FOOD),
        MenuItem("f3", "South Indian Combo", 130, ItemType.FOOD),
        MenuItem("f4", "Millet Bowl", 160, ItemType.FOOD)
    )

    val subscriptions = listOf(
        MenuItem("s1", "15 Days Plan", 1800, ItemType.SUBSCRIPTION),
        MenuItem("s2", "30 Days Plan", 3400, ItemType.SUBSCRIPTION)
    )
}
