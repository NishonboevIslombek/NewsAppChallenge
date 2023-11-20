package com.example.newsapplicationchallenge.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.newsapplicationchallenge.R
import com.example.newsapplicationchallenge.ui.theme.LightGrayProgress

@Composable
fun BookmarkIcon(
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onPrimary
) {
    IconButton(onClick = {}, modifier = modifier) {
        Icon(
            painterResource(id = R.drawable.ic_bookmark_add),
            contentDescription = "Icon Bookmark",
            tint = tint
        )
    }
}

@Composable
fun ArrowBackIcon(
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "Icon Arrow Back",
            tint = tint
        )
    }
}

@Composable
fun CircleIcon(modifier: Modifier = Modifier, tint: Color = Color.White) {
    Icon(
        painter = painterResource(id = R.drawable.ic_circle_gray),
        contentDescription = "Icon Circle",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun LikeButtonWithBackground(
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onPrimary
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .background(
                color = LightGrayProgress,
                shape = MaterialTheme.shapes.extraLarge
            )
            .padding(horizontal = 10.dp, vertical = 5.dp)

    ) {
        Icon(
            imageVector = Icons.Outlined.ThumbUp,
            contentDescription = "Icon Like",
            tint = tint
        )
        Text(text = "53")
    }
}

@Composable
fun CommentButtonWithBackground(
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onPrimary
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .background(
                color = LightGrayProgress,
                shape = MaterialTheme.shapes.extraLarge
            )
            .padding(horizontal = 10.dp, vertical = 5.dp)

    ) {
        Icon(
            imageVector = Icons.Rounded.MailOutline,
            contentDescription = "Icon Like",
            tint = tint
        )
        Text(text = "53")
    }
}

