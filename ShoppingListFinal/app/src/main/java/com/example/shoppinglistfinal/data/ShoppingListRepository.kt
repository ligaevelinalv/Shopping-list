package com.example.shoppinglistfinal.data

import androidx.lifecycle.LiveData
import com.example.shoppinglistfinal.adapter.ListItem

class ShoppingListRepository (private val shoppingListDao: ShoppingListDao) {
    val allItems: LiveData<MutableList<ListItem>> = shoppingListDao.getAllItems()

    suspend fun insertItem(item: ListItem) {
        shoppingListDao.insertItem(item)
    }

    suspend fun deleteItem(item: ListItem) {
        shoppingListDao.deleteItem(item)
    }

    suspend fun updateItem(item: ListItem) {
        shoppingListDao.updateItem(item)
    }
}