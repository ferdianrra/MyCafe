package com.dicoding.mycafe.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.mycafe.core.data.source.local.enitity.CartEntity

@Database(entities = [CartEntity::class], version = 1, exportSchema = false)
abstract class CartDatabase:RoomDatabase() {
    abstract fun cartDao():CartDao
}