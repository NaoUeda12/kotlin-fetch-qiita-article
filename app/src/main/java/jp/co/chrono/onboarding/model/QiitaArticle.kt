package jp.co.chrono.onboarding.model

import jp.co.chrono.onboarding.model.QiitaArticle
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

@Serializable
data class QiitaArticle(
    val id: String,
    val title: String,
    val user: User
)

@Serializable
data class User(
    val id: String,
    val name: String,
    @SerialName("profile_image_url")
    val profileImageUrl: String
)