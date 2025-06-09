package com.dicoding.mycafe.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartItemModel(
    val title: String,
    val imageUrl: String,
    val price: Double,
    val quantity: Int,
): Parcelable
