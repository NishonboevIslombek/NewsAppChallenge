package com.example.newsapplicationchallenge.ui.info

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapplicationchallenge.R
import com.example.newsapplicationchallenge.data.common.Constants
import com.example.newsapplicationchallenge.ui.theme.Black40
import com.example.newsapplicationchallenge.ui.utils.ArrowBackIcon
import com.example.newsapplicationchallenge.ui.utils.BookmarkIcon
import com.example.newsapplicationchallenge.ui.utils.CircleIcon
import com.example.newsapplicationchallenge.ui.utils.CommentButtonWithBackground
import com.example.newsapplicationchallenge.ui.utils.LikeButtonWithBackground
import com.example.newsapplicationchallenge.ui.utils.NewsAuthor
import com.example.newsapplicationchallenge.ui.utils.NewsType

@Composable
fun InfoScreenPortrait(onBackClicked: () -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        InfoScreen(modifier = Modifier.fillMaxSize()) { onBackClicked() }
    }
}

@Composable
fun InfoScreen(modifier: Modifier = Modifier, onBackClicked: () -> Unit) {
    val scrollState = rememberScrollState()
    val topBarAnimationFinished = remember { mutableStateOf(false) }
    val topBarAnimation = animateColorAsState(
        targetValue = if (scrollState.canScrollBackward) MaterialTheme.colorScheme.primary else Color.Transparent,
        label = "TopBarBackgroundAnimation",
        animationSpec = tween(Constants.DETAILS_SCREEN_TOP_BAR_DURATION),
        finishedListener = {
            topBarAnimationFinished.value = !topBarAnimationFinished.value
        }
    )

    Box(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(bottom = 60.dp)
        ) {
            HeaderInfoScreen()
            BodyInfoScreen(modifier = Modifier.padding(horizontal = 20.dp))
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
fun HeaderInfoScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.size(450.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.dumplings),
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
            TypeAndTimeInfoScreen()
            TitleInfoScreen()
        }
    }
}

@Composable
fun BodyInfoScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            NewsAuthor(
                authorImage = R.drawable.avatar,
                authorName = "Jean Prangley",
                textColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = MaterialTheme.shapes.extraLarge
                    )
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .weight(1f, fill = false)
            )
            LikeButtonWithBackground()
            CommentButtonWithBackground()
        }
        DescriptionInfoScreen()
    }
}

@Composable
private fun TopBarInfoScreen(modifier: Modifier = Modifier, onBackClicked: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        ArrowBackIcon { onBackClicked() }
        BookmarkIcon(tint = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
private fun TitleInfoScreen(modifier: Modifier = Modifier) {
    Text(
        text = Constants.testTopNewsList[0].first,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
    )
}

@Composable
private fun TypeAndTimeInfoScreen(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
        NewsType(type = "Food")
        CircleIcon(modifier = Modifier.size(6.dp))
        Text(
            text = "6 min ago",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
        )
    }
}

@Composable
fun DescriptionInfoScreen(modifier: Modifier = Modifier) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                append(Constants.testDescription[0])
            }

            append(Constants.testDescription.drop(1))
        },
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

