package com.dicoding.mycafe.ui.detail

import android.app.Dialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import com.dicoding.mycafe.R
import com.dicoding.mycafe.core.domain.model.CartItemModel
import com.dicoding.mycafe.core.domain.model.MenuItemModel
import com.dicoding.mycafe.ui.component.MenuCounter
import com.dicoding.mycafe.ui.component.OrderButton
import kotlinx.coroutines.launch

@Composable
fun DialogContent(
    modifier: Modifier = Modifier,
    data: MenuItemModel,
    onDismiss: () -> Unit,
) {
    var totalPoint by rememberSaveable { mutableStateOf(0.0) }
    var orderCount by rememberSaveable { mutableStateOf(0) }
    val viewModel: DetailMenuViewModel = hiltViewModel()
    Dialog(onDismissRequest = { onDismiss() }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(16.dp),
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rating Star",
                        modifier = Modifier.size(20.dp), // Ukuran ikon bintang
                        tint = Color.DarkGray // Warna ikon
                    )
                    Text(
                        text = data.rating.toString(),
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold,
                    )
                }
                AsyncImage(
                    modifier = modifier
                        .padding(top = 10.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    model = data.imageUrl,
                    contentDescription = "Menu Item"
                )
                Column(
                    modifier = modifier.padding(top = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = data.name,
                        fontSize = 20.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = modifier.padding(top = 5.dp),
                        text = stringResource(
                            R.string.prices,
                            data.price
                        ),
                        fontSize = 16.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Spacer(modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.LightGray))
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    MenuCounter(
                        1,
                        orderCount,
                        onProductIncreased = { orderCount++ },
                        onProductDecreased = { if (orderCount > 0) orderCount-- },
                        price = data.price,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 16.dp)
                    )
                    totalPoint = data.price * orderCount
                    OrderButton(
                        text = stringResource(R.string.add_to_cart, totalPoint),
                        enabled = orderCount > 0,
                        onClick = {
                            val dataCart = CartItemModel(
                                title = data.name,
                                price = data.price,
                                quantity = orderCount,
                                imageUrl = data.imageUrl
                            )
                            viewModel.insertCart(dataCart)
                            onDismiss()
                        }
                    )
                }

            }
        }
    }
}