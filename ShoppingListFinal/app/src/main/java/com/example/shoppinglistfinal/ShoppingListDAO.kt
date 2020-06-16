package com.example.shoppinglistfinal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

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