package com.example.newsapplicationchallenge.presentation.ui.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapplicationchallenge.R

@Composable
fun NewsType(
    modifier: Modifier = Modifier,
    type: String,
    color: Color = Color.Black,
    fontSize: Int = 12
) {
    Text(
        text = type,
        style = MaterialTheme.typography.labelSmall.copy(
            color = color,
            fontSize = fontSize.sp
        ),
        modifier = modifier
            .background(color = Color.White, shape = MaterialTheme.shapes.small)
            .padding(horizontal = 10.dp)
    )
}

@Composable
fun NewsAuthor(
    modifier: Modifier = Modifier,
    @DrawableRes authorImage: Int,
    authorName: String,
    textColor: Color = Color.White

) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.avatar),
//            contentDescription = "Author",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .size(24.dp)
//                .clip(CircleShape)
//        )
        Text(
            text = authorName,
            style = MaterialTheme.typography.labelSmall.copy(color = textColor),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
        )
    }
}