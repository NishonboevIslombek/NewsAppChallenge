package com.example.newsapplicationchallenge.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapplicationchallenge.data.common.Navigation
import com.example.newsapplicationchallenge.presentation.ui.info.InfoScreenPortrait
import com.example.newsapplicationchallenge.presentation.ui.home.HomeScreenPortrait
import com.example.newsapplicationchallenge.presentation.theme.NewsApplicationChallengeTheme
import com.example.newsapplicationchallenge.presentation.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsApplicationChallengeTheme(true) {
                Navigation()
            }
        }
    }
}

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
                onNewsClicked = { navController.navigate(Navigation.DetailsScreen.route) })
        }
        composable(route = Navigation.DetailsScreen.route) {
            InfoScreenPortrait {
                navController.navigateUp()
            }
        }
    }
}

