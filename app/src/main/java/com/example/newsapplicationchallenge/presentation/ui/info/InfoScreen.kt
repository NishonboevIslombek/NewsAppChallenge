package com.example.newsapplicationchallenge.presentation.ui.info

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsapplicationchallenge.R
import com.example.newsapplicationchallenge.data.common.Constants
import com.example.newsapplicationchallenge.presentation.theme.Black40
import com.example.newsapplicationchallenge.presentation.ui.utils.ArrowBackIcon
import com.example.newsapplicationchallenge.presentation.ui.utils.BookmarkIcon
import com.example.newsapplicationchallenge.presentation.ui.utils.CircleIcon
import com.example.newsapplicationchallenge.presentation.ui.utils.NewsAuthor
import com.example.newsapplicationchallenge.presentation.utils.getTime

@Composable
fun InfoScreenPortrait(
    onBackClicked: () -> Unit,
    title: String,
    imgUrl: String,
    content: String,
    time: String,
    author: String
) {
    Surface(
        color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()
    ) {
        InfoScreen(title = title,
            imgUrl = imgUrl,
            content = content,
            time = time,
            author = author,
            modifier = Modifier.fillMaxSize(),
            onBackClicked = { onBackClicked() })
    }
}

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    title: String,
    imgUrl: String,
    content: String,
    time: String,
    author: String
) {
    val scrollState = rememberScrollState()
    val topBarAnimationFinished = remember { mutableStateOf(false) }
    val topBarAnimation =
        animateColorAsState(targetValue = if (scrollState.canScrollBackward) MaterialTheme.colorScheme.primary else Color.Transparent,
            label = "TopBarBackgroundAnimation",
            animationSpec = tween(Constants.DETAILS_SCREEN_TOP_BAR_DURATION),
            finishedListener = {
                topBarAnimationFinished.value = !topBarAnimationFinished.value
            })

    Box(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(bottom = 60.dp)
        ) {
            HeaderInfoScreen(title = title, imgUrl = imgUrl, time = time, author = author)
            BodyInfoScreen(
                content = content, modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
        TopBarInfoScreen(
            modifier = Modifier
                .shadow(if (scrollState.canScrollBackward && topBarAnimationFinished.value) 10.dp else 0.dp)
                .background(topBarAnimation.value)
                .padding(top = 40.dp)
        ) { onBackClicked() }
    }
}

@Composable
fun HeaderInfoScreen(
    modifier: Modifier = Modifier, title: String, imgUrl: String, time: String, author: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp)
    ) {
        AsyncImage(
            model = imgUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(listOf(Black40, Color.Transparent)))
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {
            AuthorAndTimeInfoScreen(time = time, author = author)
            TitleInfoScreen(title = title)
        }
    }
}

@Composable
fun BodyInfoScreen(modifier: Modifier = Modifier, content: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp), modifier = modifier.fillMaxWidth()
    ) {
        ContentInfoScreen(content = content)
    }
}

@Composable
private fun TopBarInfoScreen(modifier: Modifier = Modifier, onBackClicked: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxWidth()
    ) {
        ArrowBackIcon { onBackClicked() }
        BookmarkIcon(tint = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
private fun TitleInfoScreen(modifier: Modifier = Modifier, title: String) {
    Text(
        text = title, style = MaterialTheme.typography.titleLarge, modifier = modifier
    )
}

@Composable
private fun AuthorAndTimeInfoScreen(
    modifier: Modifier = Modifier, time: String, author: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
        Text(
            text = time.getTime(), style = MaterialTheme.typography.labelSmall, modifier = Modifier
        )
        CircleIcon(modifier = Modifier.size(6.dp))
        NewsAuthor(authorImage = R.drawable.avatar, authorName = author)
    }
}

@Composable
fun ContentInfoScreen(modifier: Modifier = Modifier, content: String) {
    Text(
        text = content, style = MaterialTheme.typography.bodyMedium, modifier = modifier
    )
}

