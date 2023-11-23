package com.example.newsapplicationchallenge.presentation.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.newsapplicationchallenge.data.common.Constants
import com.example.newsapplicationchallenge.domain.model.NewsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenPortrait(onNewsClicked: () -> Unit, uiState: HomeUiState) {
    val currentUiState = rememberUpdatedState(newValue = uiState)
    Surface(color = MaterialTheme.colorScheme.background) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp),
            topBar = {
                TopBarHomeScreen(modifier = Modifier.padding(horizontal = 20.dp))
            }
        ) {
            if (currentUiState.value.isLoading) CircularProgressIndicator(color = Color.Blue)
            if (!currentUiState.value.isLoading)
                HomeScreen(
                    list = uiState.topNews,
                    modifier = Modifier
                        .padding(it)
                        .padding(top = 20.dp), onNewsClicked = { onNewsClicked() }
                )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    onNewsClicked: () -> Unit,
    list: List<NewsItem>
) {
    val pagerState = rememberPagerState { Constants.testHomeTabList.size }
    val tabsList = remember { Constants.testHomeTabList.map { it.second } }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .fillMaxSize()
    ) {
        TabsRowHomeScreen(
            pageCount = pagerState.pageCount,
            pagerIndex = pagerState.currentPage,
            tabsList = tabsList,
            onTabClicked = { tabIndex ->
                pagerState.animateScrollToPage(tabIndex)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        ViewPagerHomeScreen(
            pagerState = pagerState,
            topNews = list,
            onNewsClicked = { onNewsClicked() })
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ViewPagerHomeScreen(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onNewsClicked: () -> Unit,
    topNews: List<NewsItem>
) {
    HorizontalPager(
        state = pagerState,
        modifier = modifier
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(vertical = 10.dp)
        ) {
//            item { TopNewsSection() }
            items(topNews) {
                SimpleNewsElement(
                    title = it.title,
                    imgUrl = it.urlToImage,
                    modifier = Modifier
                        .clickable {
                            onNewsClicked()
                        }
                        .padding(
                            horizontal = 20.dp
                        )
                )
            }
        }
    }
}

@Composable
private fun TopBarHomeScreen(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Rounded.Menu,
            contentDescription = "Menu",
            tint = MaterialTheme.colorScheme.onPrimary
        )
        Icon(
            imageVector = Icons.Rounded.Search,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun TabsRowHomeScreen(
    modifier: Modifier = Modifier,
    pageCount: Int,
    pagerIndex: Int,
    tabsList: List<String>,
    onTabClicked: suspend (rowIndex: Int) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        repeat(pageCount) {
            TabElementHomeScreen(
                label = tabsList[it],
                page = Pair(it, pagerIndex),
                action = { onTabClicked(it) })
        }
    }
}

@Composable
private fun TabElementHomeScreen(
    modifier: Modifier = Modifier,
    label: String,
    page: Pair<Int, Int>,
    action: suspend () -> Unit
) {
    val (rowIndex, pagerIndex) = page
    val scope: CoroutineScope = rememberCoroutineScope()

    Text(
        text = label,
        style = if (rowIndex == pagerIndex) MaterialTheme.typography.labelMedium.copy(
            color = MaterialTheme.colorScheme.onPrimary
        )
        else MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.secondary
        ),
        modifier = modifier.clickable {
            scope.launch(Dispatchers.Main) { action() }
        }
    )
}