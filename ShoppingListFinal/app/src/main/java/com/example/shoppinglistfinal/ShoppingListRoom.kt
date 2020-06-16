package com.example.shoppinglistfinal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ListItem::class), version = 1, exportSchema = false)
public abstract class ShoppingListRoomDatabase: RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListDao

    companion object {
        @Volatile
        private var INSTANCE: ShoppingListRoomDatabase? = null

        fun getDatabase(context: Context): ShoppingListRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingListRoomDatabase::class.java,
                    "shopping_list_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }



    }
}