package com.example.shoppinglistfinal.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoppinglistfinal.adapter.ListItem

@Dao
interface  ShoppingListDao {
    @Query("SELECT * FROM shopping_list_table")
    fun getAllItems(): LiveData<MutableList<ListItem>>

//    suspend fun only works in coroutines
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(item: ListItem)

    @Delete
    suspend fun deleteItem(item: ListItem)

    @Update
    suspend fun updateItem(item: ListItem)
}