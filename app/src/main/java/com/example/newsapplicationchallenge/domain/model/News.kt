package com.example.newsapplicationchallenge.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class NewsResponse(
    @Json(name = "status") var status: String,
    @Json(name = "articles") var articles: List<NewsItem>
)

@JsonClass(generateAdapter = true)
data class NewsSource(
    @Json(name = "id")
    val id: String?
):Serializable

@JsonClass(generateAdapter = true)
data class NewsItem(
    @Json(name = "source")
    val source: NewsSource,

    @Json(name = "title")
    val title: String,

    @Json(name = "author")
    val author: String?,

    @Json(name = "description")
    val description: String?,

    @Json(name = "urlToImage")
    val urlToImage: String?,

    @Json(name = "publishedAt")
    val publishedAt: String,

    @Json(name = "content")
    val content: String? = null
) : Serializable

