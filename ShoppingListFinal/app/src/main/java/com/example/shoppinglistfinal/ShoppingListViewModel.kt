package com.example.shoppinglistfinal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingListViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ShoppingListRepository
    val allItems: LiveData<MutableList<ListItem>>

    init {
        val ShoppingListDao = ShoppingListRoomDatabase.getDatabase(application).shoppingListDao()
        repository = ShoppingListRepository(ShoppingListDao)
        allItems = repository.allItems
    }

    fun insertItem(item: ListItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertItem(item)
    }

    fun deleteItem(item: ListItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteItem(item)
    }

    fun updateItem(item: ListItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateItem(item)
    }
}