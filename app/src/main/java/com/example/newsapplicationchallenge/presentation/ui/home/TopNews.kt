package com.example.newsapplicationchallenge.presentation.ui.home

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsapplicationchallenge.R
import com.example.newsapplicationchallenge.data.common.Constants
import com.example.newsapplicationchallenge.data.common.Constants.testTopNewsList
import com.example.newsapplicationchallenge.presentation.theme.Black25
import com.example.newsapplicationchallenge.presentation.theme.LightGrayProgress
import com.example.newsapplicationchallenge.presentation.ui.utils.BookmarkIcon
import com.example.newsapplicationchallenge.presentation.ui.utils.CircleIcon
import com.example.newsapplicationchallenge.presentation.ui.utils.NewsAuthor
import com.example.newsapplicationchallenge.presentation.ui.utils.NewsType
import com.example.newsapplicationchallenge.presentation.utils.countDownForAction

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopNewsSection(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState { testTopNewsList.size }
    val scope = rememberCoroutineScope()
    val topNews = remember { testTopNewsList }
    val infiniteTransition = rememberInfiniteTransition(label = "Indicator Animation")

    val indicatorProgress = infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = if (pagerState.currentPage == pagerState.settledPage) 1F else 0F,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = Constants.TOP_NEWS_PAGER_SCROLL_DURATION.toInt()
            }
        ),
        label = "Indicator Progress"
    )

    DisposableEffect(key1 = pagerState.currentPage) {
        val countDownTimer =
            scope.countDownForAction(millis = Constants.TOP_NEWS_PAGER_SCROLL_DURATION,
                interval = Constants.TOP_NEWS_PAGER_SCROLL_INTERVAL,
                action = {
                    if (pagerState.canScrollForward) pagerState.animateScrollToPage(pagerState.settledPage + 1)
                    else pagerState.animateScrollToPage(0)
                }).start()
        onDispose { countDownTimer.cancel() }
    }


    Box(
        modifier = modifier
            .wrapContentHeight(align = Alignment.Top)
    ) {
        ViewPagerTopNews(
            pagerState = pagerState,
            titles = topNews.map { it.first },
            imageUrls = topNews.map { it.second })

        IndicatorsRowTopNews(
            pagerState = pagerState,
            indicatorState = indicatorProgress.value,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ViewPagerTopNews(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    titles: List<String>,
    imageUrls: List<String>
) {
    HorizontalPager(state = pagerState, modifier = modifier) {
        CardElementTopNews(
            title = titles[it],
            imageUrl = imageUrls[it]
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun IndicatorsRowTopNews(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    indicatorState: Float
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),

        ) {
        repeat(pagerState.pageCount) { pageIndex ->
            IndicatorElementTopNews(
                pagesIndex = Pair(pageIndex, pagerState.settledPage),
                indicatorState = indicatorState,
                modifier = Modifier
                    .padding(2.dp)
                    .clip(MaterialTheme.shapes.extraSmall)
                    .width(32.dp)
                    .height(5.dp)
            )
        }
    }

}

@Composable
private fun IndicatorElementTopNews(
    modifier: Modifier = Modifier,
    pagesIndex: Pair<Int, Int>,
    indicatorState: Float
) {
    LinearProgressIndicator(
        progress = if (pagesIndex.second == pagesIndex.first) indicatorState else 0f,
        color = MaterialTheme.colorScheme.tertiary,
        trackColor = LightGrayProgress,
        modifier = modifier
    )
}


@Composable
fun CardElementTopNews(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(horizontal = 20.dp)
            .clip(MaterialTheme.shapes.extraLarge)

    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "TopNewsImage",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Black25)
        )

        BookmarkIcon(
            modifier = Modifier
                .padding(20.dp)
                .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                .size(36.dp)
                .padding(8.dp)
                .align(Alignment.TopEnd)
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    horizontal = 20.dp, vertical = 50.dp
                )
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(top = 15.dp)
            ) {

                NewsAuthor(
                    authorImage = R.drawable.avatar,
                    authorName = "Jean Prangley",
                    modifier = Modifier
                        .weight(1f, fill = false)
                )

                CircleIcon(modifier = Modifier.size(6.dp))

                Text(
                    text = "6 min ago",
                    style = MaterialTheme.typography.labelSmall
                )

                CircleIcon(modifier = Modifier.size(6.dp))

                NewsType(type = "Food")
            }
        }
    }
}
