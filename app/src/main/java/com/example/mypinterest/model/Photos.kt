package com.example.mypinterest.model

import androidx.room.Entity

typealias Photos = ArrayList<Photo>

//@Entity(tableName = "photo_table")
data class Photo(
    val alt_description: Any? = null,
    val blur_hash: String? = null,
    val categories: List<Any>? = null,
    val color: String? = null,
    val created_at: String? = null,
    val current_user_collections: List<Any>? = null,
    val description: String? = null,
    val height: Int? = null,
    val id: String? = null,
    val liked_by_user: Boolean? = null,
    val likes: Int? = null,
    val links: PhotoLinks? = null,
    val promoted_at: String? = null,
    val sponsorship: Sponsorship? = null,
    val topic_submissions: TopicSubmissions? = null,
    val updated_at: String? = null,
    val urls: Urls? = null,
    val user: User? = null,
    val width: Int? = null,
)

data class PhotoLinks(
    val download: String? = null,
    val download_location: String? = null,
    val html: String? = null,
    val self: String? = null
)

data class Sponsorship(
    val impressionUrls: List<String>?,
    val tagline: String?,
    val taglineURL: String?,
    val sponsor: User?,
)

data class User(
    val accepted_tos: Boolean? = null,
    val bio: String? = null,
    val first_name: String? = null,
    val for_hire: Boolean? = null,
    val id: String? = null,
    val instagram_username: String? = null,
    val last_name: Any? = null,
    val links: UserLinks? = null,
    val location: String? = null,
    val name: String? = null,
    val portfolio_url: Any? = null,
    val profile_image: ProfileImage? = null,
    val social: Social? = null,
    val total_collections: Int? = null,
    val total_likes: Int? = null,
    val total_photos: Int? = null,
    val twitter_username: Any? = null,
    val updated_at: String? = null,
    val username: String? = null
)

data class UserLinks(
    val self: String?,
    val html: String?,
    val photos: String?,
    val likes: String?,
    val portfolio: String?,
    val following: String?,
    val followers: String?,
)

data class ProfileImage(
    val small: String?,
    val medium: String?,
    val large: String?,
)

data class Social(
    val instagram_username: String? = null,
    val portfolio_url: String? = null,
    val twitter_username: String? = null,
    val paypal_email: Any? = null,
)


data class ArtsCulture(
    val status: String?,
    val approved_on: String?,
)

data class Urls(
    val raw: String?,
    val full: String?,
    val regular: String?,
    val small: String?,
    val thumb: String?,
    val smallS3: String?,
)
