package com.example.shoppinglistfinal.adapter

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list_table")
data class ListItem (
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "item_col") val text1: String,
    @ColumnInfo(name = "check_col") var ischecked: Boolean
)