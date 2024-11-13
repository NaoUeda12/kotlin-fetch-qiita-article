package jp.co.chrono.onboarding

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QiitaApiInterface {

    // GETリクエストで`Response<List<Article>>`を返す
    @GET("items")
    suspend fun getArticles(
        @Query("query") query: String?
    ): Response<List<Article>>
}

data class Article(
    val id: String,
    val title: String,
    val user: User,
)

data class User(
    val id: String,
    val name: String,
    val profile_image_url: String,
)

