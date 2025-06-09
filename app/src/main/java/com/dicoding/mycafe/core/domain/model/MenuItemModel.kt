package com.dicoding.mycafe.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuItemModel(
    val imageUrl: String,
    val name: String,
    val price: Double,
    val rating: Double,
    val stock: Double
): Parcelable