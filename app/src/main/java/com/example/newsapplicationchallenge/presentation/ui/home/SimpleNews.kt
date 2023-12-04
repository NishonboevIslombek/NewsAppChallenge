package com.example.newsapplicationchallenge.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.newsapplicationchallenge.presentation.ui.utils.BookmarkIcon
import com.example.newsapplicationchallenge.presentation.utils.getTime

@Composable
fun SimpleNewsElement(
    modifier: Modifier = Modifier,
    title: String,
    imgUrl: String?,
    publishedAt: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        SubcomposeAsyncImage(
            model = imgUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            loading = {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(36.dp)
                    )
                }
            },
            modifier = Modifier
                .width(80.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Column(
            modifier = Modifier
                .padding(start = 15.dp)
                .weight(1f, true)
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.onPrimary),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = publishedAt.getTime(),
                style = MaterialTheme.typography.labelSmall
                    .copy(color = MaterialTheme.colorScheme.secondary)
            )

        }
        BookmarkIcon(
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(end = 10.dp, bottom = 10.dp)
        )
    }
}