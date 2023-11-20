package com.example.newsapplicationchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapplicationchallenge.data.Navigation
import com.example.newsapplicationchallenge.ui.info.InfoScreenPortrait
import com.example.newsapplicationchallenge.ui.home.HomeScreenPortrait
import com.example.newsapplicationchallenge.ui.theme.NewsApplicationChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsApplicationChallengeTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Navigation.HomeScreen.route) {
        composable(route = Navigation.HomeScreen.route) {
            HomeScreenPortrait {
                navController.navigate(Navigation.DetailsScreen.route)
            }
        }
        composable(route = Navigation.DetailsScreen.route) {
            InfoScreenPortrait {
                navController.navigateUp()
            }
        }
    }
}

