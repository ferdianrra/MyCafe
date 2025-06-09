package com.dicoding.mycafe.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderModel(
    val noTable: String,
    val isCompleted: Boolean,
    val totalPrice: Double,
    val date: String
): Parcelable
