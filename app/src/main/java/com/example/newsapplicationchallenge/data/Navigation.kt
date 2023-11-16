package com.example.newsapplicationchallenge.data

sealed class Navigation(val route: String) {
    object HomeScreen : Navigation("home_screen")
    object DetailsScreen : Navigation("details_screen")

}