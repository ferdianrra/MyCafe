package com.dicoding.mycafe.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dicoding.mycafe.R
import com.dicoding.mycafe.ui.theme.MyCafeTheme

@Composable
fun CartItem(
    title: String,
    imageUrl: String,
    price: Double,
    quantity: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row (
        modifier = modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth()
                .height(160.dp)
                .padding( vertical = 15.dp )
                .shadow(14.dp, shape = RoundedCornerShape(16.dp))
                .background(color = colorResource(id = R.color.white)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Cart Item",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(start = 20.dp)
                .clip(CircleShape)
                .size(90.dp)
            )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = stringResource(
                    R.string.prices,
                    price
                ),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall,
            )
            Text(
                text = "Quantity: $quantity",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall,
            )
        }
        Icon(
            imageVector = Icons.Filled.Clear,
            contentDescription = "Rating Star",
            modifier = Modifier
                .padding(20.dp)
                .size(20.dp)
                .align(Alignment.Top)
                .clickable { onClick() }
            , // Ukuran ikon bintang
            tint = Color.Red // Warna ikon
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCartItem() {
    MyCafeTheme {
        CartItem(title = "Pasta", imageUrl = "", price = 40000.00, quantity = 5, onClick = {})
    }
}

