package com.dicoding.mycafe.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderItem(
    val noTable: String,
    val isCompleted: Boolean,
    val totalPrice: Double,
    val date: String
) : Parcelable