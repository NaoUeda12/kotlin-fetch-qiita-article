package jp.co.chrono.onboarding

import retrofit.RestAdapter
import java.io.IOException

class QiitaRepository() {
    private var service: QiitaApiInterface = Retrofit.Builder()
        .baseUrl("https://qiita.com/api/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(QiitaApiInterface::class.java)

    // Qiita記事を取得するメソッド
    fun getArticles(query: String?): List<QiitaApiInterface.Article>? {
        try {
            val response = service.getArticles(query).execute()

            if (response.isSuccessful) {
                return response.body()
            } else { // 失敗の時は今回は実装していません。
                RestAdapter.Log.d("QiitaRepository", "GET ERROR")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}