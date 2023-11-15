package com.example.newsapplicationchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.newsapplicationchallenge.ui.details.DetailsScreen
import com.example.newsapplicationchallenge.ui.details.DetailsScreenPortrait
import com.example.newsapplicationchallenge.ui.home.HomeScreenPortrait
import com.example.newsapplicationchallenge.ui.theme.NewsApplicationChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsApplicationChallengeTheme {
                HomeScreenPortrait()
            }
        }
    }
}
