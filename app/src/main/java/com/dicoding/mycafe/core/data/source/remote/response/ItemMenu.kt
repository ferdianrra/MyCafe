package com.dicoding.mycafe.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemMenu(
    val imageUrl: String,
    val name: String,
    val price: Double,
    val rating: Double,
    val stock: Double
) : Parcelable