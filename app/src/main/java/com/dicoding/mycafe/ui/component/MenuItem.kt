package com.dicoding.mycafe.ui.component

import android.media.Rating
import android.provider.CalendarContract.Colors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dicoding.mycafe.R
import com.dicoding.mycafe.ui.theme.MyCafeTheme

@Composable
fun MenuItem(
    title: String,
    price: Double,
    rating: Double,
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(155.dp)
            .height(240.dp)
            .shadow(14.dp, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(color = colorResource(id = R.color.white))


        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier.fillMaxWidth()
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
                text = rating.toString(),
                fontSize = 14.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
            )
        }
        AsyncImage(
            model = imageUrl, contentDescription = "Menu Image",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(10.dp)
                .clip(CircleShape)
                .size(120.dp)
        )
        Text(
            text = title,
            fontSize = 15.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(
                R.string.prices,
                price
            ),
            fontSize = 14.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
        )


    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMenuItem() {
    MyCafeTheme {
        MenuItem(title = "Pasta", price = 170000.00, rating = 4.5, imageUrl = "")
    }
}