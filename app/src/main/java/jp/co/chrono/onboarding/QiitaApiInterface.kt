package jp.co.chrono.onboarding


interface QiitaApiInterface {

    annotation class Query(val value: String)

    // GET
    @GET("items")
    suspend fun getArticles(
        @Query("query") query: String?
    ): List<Article>

}
annotation class GET(val value: String)

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