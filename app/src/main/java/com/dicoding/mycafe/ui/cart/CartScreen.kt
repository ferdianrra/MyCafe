@file:Suppress("UNCHECKED_CAST")

package com.dicoding.mycafe.ui.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mycafe.R
import com.dicoding.mycafe.core.domain.model.CartItemModel
import com.dicoding.mycafe.ui.common.UiState
import com.dicoding.mycafe.ui.component.CartItem
import com.dicoding.mycafe.ui.component.OrderButton
import com.dicoding.mycafe.ui.component.ShowError
import com.dicoding.mycafe.ui.component.ShowLoading
import kotlinx.coroutines.launch

@Composable
fun CartScreen(modifier: Modifier = Modifier) {
    val viewModel: CartViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.viewModelScope.launch {
            viewModel.getCart()
        }
    }
    when(uiState) {
        is UiState.Error -> {
            ShowError {
                viewModel.viewModelScope.launch {
                    viewModel.getCart()
                }
            }
        }
        UiState.Loading ->  {
            ShowLoading()
        }
        is UiState.Success -> {
            val list = (uiState as UiState.Success<List<CartItemModel>>).data as List<CartItemModel>
            if (list.isEmpty()) {
                Column(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No Orders",
                        fontSize = 24.sp,
                    )
                }
            } else {
                CartContent(
                    listCart = list,
                    viewModel = viewModel,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }

    }
}

@Composable
fun CartContent(
    modifier: Modifier = Modifier,
    listCart: List<CartItemModel>,
    viewModel: CartViewModel
) {
    val totalPrices = listCart.sumOf {
        it.price*it.quantity }
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        DialogPayment(
           totalPrice = totalPrices, onDismiss = {showDialog = false}
        )
    }

    Column(
        modifier = modifier.padding(horizontal = 20.dp)
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = modifier.padding(top = 40.dp),
        ) {
            items(listCart) { data ->
                CartItem(
                    title = data.title,
                    imageUrl = data.imageUrl,
                    price = data.price,
                    quantity = data.quantity,
                    onClick = {
                        viewModel.deleteCart(data)
                        viewModel.getCart()
                    }
                )
            }
        }
        
        OrderButton(
            modifier = modifier.padding(top = 20.dp),
            text = stringResource(id = R.string.make_a_payment, totalPrices),
            onClick = {showDialog = true}
            )
    }
}