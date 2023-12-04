package com.example.newsapplicationchallenge.presentation.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapplicationchallenge.data.common.Navigation
import com.example.newsapplicationchallenge.presentation.ui.info.InfoScreenPortrait
import com.example.newsapplicationchallenge.presentation.ui.home.HomeScreenPortrait
import com.example.newsapplicationchallenge.presentation.theme.NewsApplicationChallengeTheme
import com.example.newsapplicationchallenge.presentation.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsApplicationChallengeTheme {
                Navigation()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Navigation(
    viewModel: HomeViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()
    NavHost(navController = navController, startDestination = Navigation.HomeScreen.route) {
        composable(route = Navigation.HomeScreen.route) {
            HomeScreenPortrait(
                uiState = uiState,
                onNewsClicked = {
                    navController.navigate(
                        Navigation.DetailsScreen.route + "/${it.title}" +
                                "/${
                                    URLEncoder.encode(
                                        it.urlToImage,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                }" +
                                "/${it.content ?: ""}" + "/${it.publishedAt}" + "/${
                            if (it.author != null && it.author.startsWith("https"))
                                it.author.split("/").last() else it.author
                        }"
                    )
                })
        }
        composable(
            route = Navigation.DetailsScreen.route + "/{title}/{imgUrl}/{content}/{publishedAt}/{author}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                },
                navArgument("imgUrl") {
                    type = NavType.StringType
                },
                navArgument("content") {
                    type = NavType.StringType
                },
                navArgument("publishedAt") {
                    type = NavType.StringType
                },
                navArgument("author") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            InfoScreenPortrait(
                title = it.arguments?.getString("title") ?: "",
                imgUrl = it.arguments?.getString("imgUrl") ?: "",
                content = it.arguments?.getString("content") ?: "",
                time = it.arguments?.getString("publishedAt") ?: "",
                author = it.arguments?.getString("author") ?: "BBC",
                onBackClicked = { navController.navigateUp() }
            )
        }
    }
}

