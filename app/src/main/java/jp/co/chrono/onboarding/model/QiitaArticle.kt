package jp.co.chrono.onboarding.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QiitaArticle(
    val id: String, // 記事のID
    val title: String, // 記事のタイトル
    val user: User // 投稿者情報
)

@Serializable
data class User(
    val id: String, // ユーザーID
    val name: String, // ユーザー名
    @SerialName("profile_image_url")
    val profileImageUrl: String // プロフィール画像のURL
)