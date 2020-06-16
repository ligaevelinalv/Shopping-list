package com.example.shoppinglistfinal

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Update

class ShoppingListRepository (private val shoppingListDao: ShoppingListDao) {
    val allItems: LiveData<MutableList<ListItem>> = shoppingListDao.getAllItems()

    suspend fun insertItem(item: ListItem) {
        shoppingListDao.insertItem(item)
    }

//    suspend fun deleteAll()

    suspend fun deleteItem(item: ListItem) {
        shoppingListDao.deleteItem(item)
    }

    suspend fun updateItem(item: ListItem) {
        shoppingListDao.updateItem(item)
    }
}