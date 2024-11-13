package jp.co.chrono.onboarding

import okhttp3.Call
import retrofit.http.GET
import retrofit.http.Query
import retrofit2.http.GET
import retrofit2.http.Query

interface QiitaApiInterface {

        // GET
        @GET("items")
        suspend fun getArticles(
            @Query("query") query: String?
        ): Call<List<Article>>

    }

    data class Article (
        val id: String,
        val title: String,
        val user: User,
    )

    data class User (
        val id: String,
        val name: String,
        val profile_image_url: String,
    )

}