package jp.co.chrono.onboarding.network

import com.squareup.moshi.Json
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL =
    "https://qiita.com/api/v2"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

private fun String.toMediaType(): Any {

}

interface QiitaApiInterface {
    @GET("articles")
    suspend fun getArticles(): List<QiitaAriticle>
}

class QiitaAriticle {

}

object QiitaApi {
    val retrofitService: QiitaApiInterface by lazy {
        retrofit.create(QiitaApiInterface::class.java)
    }
}