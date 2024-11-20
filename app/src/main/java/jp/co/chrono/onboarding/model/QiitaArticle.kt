package jp.co.chrono.onboarding.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QiitaArticle(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "url") val url: String,
    @Json(name = "user") val user: User,
)

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "profile_image_url") val profileImageUrl: String,
)
