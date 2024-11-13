import android.util.Log
import jp.co.chrono.onboarding.Article
import jp.co.chrono.onboarding.QiitaApiInterface
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

class QiitaRepository {
    private val service: QiitaApiInterface = Retrofit.Builder()
        .baseUrl("https://qiita.com/api/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(QiitaApiInterface::class.java)


    suspend fun getArticles(query: String?): List<Article>? {
        return try {
            val response = service.getArticles(query) // 非同期呼び出し

            if (response.isSuccessful) {
                response.body()
            } else {
                Log.d("QiitaRepository", "GET ERROR")
                null
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}