package com.dicoding.mycafe.ui.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dicoding.mycafe.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderItem(
    noTable: String,
    date: String,
    price: Double,
    isCompleted: Boolean,
    modifier: Modifier = Modifier,
) {
    Row (
        modifier = modifier
            .padding(horizontal = 5.dp)
            .fillMaxWidth()
            .height(160.dp)
            .padding(vertical = 15.dp)
            .shadow(14.dp, shape = RoundedCornerShape(16.dp))
            .background(color = colorResource(id = R.color.white)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        val dateTime = LocalDateTime.parse(date)
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val formattedDateTime = dateTime.format(formatter)

        Icon(
            imageVector = Icons.Filled.DateRange,
            contentDescription = "Cart Item",
            modifier = modifier
                .padding(start = 20.dp)
                .clip(RoundedCornerShape(10.dp))
                .size(60.dp)
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = "Table Number: $noTable",
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
                text = "Date: $formattedDateTime",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall,
            )
        }
        Text(
            modifier = modifier
                .padding(end = 20.dp, top = 20.dp)
                .align(alignment = Alignment.Top),
            text = "On Going",
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.blue),
            style = MaterialTheme.typography.titleSmall,
        )
    }
}