package jp.co.chrono.onboarding.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import jp.co.chrono.onboarding.model.QiitaArticle
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// APIのベースURL
private const val BASE_URL = "https://qiita.com/api/v2/"

// 認証トークン
private const val ACCESS_TOKEN = "your_access_token_here"

// Moshiの設定
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// ログインタセプター
private val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.HEADERS
}

// 認証インタセプター
private val authInterceptor = Interceptor { chain ->
    val original = chain.request()
    val request = original.newBuilder()
//        .header("Authorization", "Bearer $ACCESS_TOKEN")
        .method(original.method, original.body)
        .build()
    chain.proceed(request)
}

// OkHttpClientの設定
private val client = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor) // ログ用インタセプターを追加
    .addInterceptor(authInterceptor)    // 認証用インタセプターを追加
    .build()

// Retrofitの設定
private val retrofit = Retrofit.Builder()
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// APIインターフェース
interface QiitaApiInterface {
    // クエリを渡せるようにする
    @GET("items")
    suspend fun getItems(
        @Query("query") query: String,          // クエリパラメータ
        @Query("page") page: Int = 1,           // ページ番号 (デフォルトは1)
        @Query("per_page") perPage: Int = 20    // 1ページあたりのアイテム数 (デフォルトは20)
    ): List<QiitaArticle>
}

// Qiita APIオブジェクト
object QiitaApi {
    val retrofitService: QiitaApiInterface by lazy {
        retrofit.create(QiitaApiInterface::class.java)
    }
}
