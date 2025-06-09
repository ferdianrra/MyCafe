package com.dicoding.mycafe.core.utils

import com.dicoding.mycafe.core.data.source.local.enitity.CartEntity
import com.dicoding.mycafe.core.data.source.remote.response.ItemMenu
import com.dicoding.mycafe.core.data.source.remote.response.OrderItem
import com.dicoding.mycafe.core.domain.model.CartItemModel
import com.dicoding.mycafe.core.domain.model.MenuItemModel
import com.dicoding.mycafe.core.domain.model.OrderModel

object DataMapper {
    fun mapResponseToDomain(input: List<ItemMenu>): List<MenuItemModel> {
        val menuList = ArrayList<MenuItemModel>()
        input.map {
            val menu = MenuItemModel(
                imageUrl = it.imageUrl,
                price = it.price,
                name = it.name,
                rating = it.rating,
                stock = it.stock
            )
            menuList.add(menu)
        }
        return menuList
    }

    fun mapResponseOrderToDomain(input: List<OrderItem>): List<OrderModel> {
        val menuList = ArrayList<OrderModel>()
        input.map {
            val order = OrderModel(
                noTable = it.noTable,
                date = it.date,
                totalPrice = it.totalPrice,
                isCompleted = it.isCompleted
            )
            menuList.add(order)
        }
        return menuList
    }

    fun mapEntityToDomain(entity: List<CartEntity>) =
        entity.map { input ->
            CartItemModel(
                title = input.title,
                imageUrl = input.imageUrl,
                price = input.price,
                quantity = input.quantity
            )
        }


    fun mapDomainToEntity(input: CartItemModel) = CartEntity(
        title = input.title,
        price = input.price,
        quantity = input.quantity,
        imageUrl = input.imageUrl
    )
}