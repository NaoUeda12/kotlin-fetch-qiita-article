package jp.co.chrono.onboarding

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

class QiitaRepository() {
    private val service: QiitaApiInterface = Retrofit.Builder()
        .baseUrl("https://api.qiita.com/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(QiitaApiInterface::class.java)

    // Qiita記事を取得するメソッド
    suspend fun getArticles(query: String?): List<Article>? {

        return try {

            val articles = service.getArticles(query)

            articles
        } catch (e: IOException) {

            Log.e("QiitaRepository", "Error fetching articles", e)
            emptyList()
        }
    }
}