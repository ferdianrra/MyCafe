package com.dicoding.mycafe.ui.cart

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.dicoding.mycafe.ui.cart.order.OrderViewModel
import com.dicoding.mycafe.ui.component.OrderButton
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogPayment(
    modifier: Modifier = Modifier,
    totalPrice: Double,
    onDismiss: () -> Unit,
) {
    var text by remember { mutableStateOf("") }
    val viewModel: OrderViewModel = hiltViewModel()
    val viemodelCart: CartViewModel = hiltViewModel()
    val currentDate = LocalDateTime.now().toString()
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
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Information Icon",
                    modifier = modifier
                        .padding(vertical = 10.dp)
                        .size(100.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    tint = Color.Gray
                )
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Enter your table number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Column(
                    modifier = modifier.padding(top = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = String.format("The total price of your order is \n Rp.%,.2f",totalPrice),
                        fontSize = 20.sp,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = modifier.padding(top = 5.dp),
                        text = "Do you want to continue the Payment?",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray,
                    )
                    Spacer(modifier = Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Color.LightGray))

                    OrderButton(
                        text = "Make payments with Cash",
                        onClick = {
                            if(text.isNotEmpty()) {
                                viewModel.sendOrder(noTable = "k", totalPrice = totalPrice, date = currentDate, isCompleted = false)
                                viemodelCart.deleteAllCart()
                                onDismiss()
                            } 
                        })
                    }
                Spacer(modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray))
                    OrderButton(
                        text = "Make non-cash payments",
                        onClick = {
                            if(text.isNotEmpty()) {
                                viewModel.sendOrder(noTable = text, totalPrice = totalPrice, date = currentDate, isCompleted = false)
                                viemodelCart.deleteAllCart()
                                onDismiss()
                            }
                        }
                    )
                }

            }
        }
    }

