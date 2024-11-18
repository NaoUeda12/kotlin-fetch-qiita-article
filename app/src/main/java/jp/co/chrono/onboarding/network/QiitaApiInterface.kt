package jp.co.chrono.onboarding.network


import com.squareup.moshi.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL =
    "https://qiita.com/api/v2"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()


interface QiitaApiInterface {
    @GET("articles")
    suspend fun getPhotos(): List<QiitaAriticle>
}


object QiitaApi {
    val retrofitService: QiitaApiService by lazy {
        retrofit.create(QiitaApiService::class.java)
    }
}