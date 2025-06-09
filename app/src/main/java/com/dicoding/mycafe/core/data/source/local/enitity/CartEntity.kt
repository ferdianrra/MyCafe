package com.dicoding.mycafe.core.data.source.local.enitity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "cart")
data class CartEntity (
    @PrimaryKey
    @Nonnull
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String,

    @ColumnInfo(name = "price")
    var price: Double,

    @ColumnInfo(name = "quantity")
    var quantity: Int
)