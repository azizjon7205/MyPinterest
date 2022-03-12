package com.example.mypinterest.model


data class Profile (
    val id: String? = null,
    val updatedAt: String? = null,
    val username: String? = null,
    val name: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val twitterUsername: Any? = null,
    val portfolioURL: Any? = null,
    val bio: Any? = null,
    val location: Any? = null,
    val links: Links? = null,
    val profileImage: ProfileImage? = null,
    val instagramUsername: Any? = null,
    val totalCollections: Long? = null,
    val totalLikes: Long? = null,
    val totalPhotos: Long? = null,
    val acceptedTos: Boolean? = null,
    val forHire: Boolean? = null,
    val social: Social? = null,
    val followedByUser: Boolean? = null,
    val photos: List<Any?>? = null,
    val badge: Any? = null,
    val tags: Tags? = null,
    val followersCount: Long? = null,
    val followingCount: Long? = null,
    val allowMessages: Boolean? = null,
    val numericID: Long? = null,
    val downloads: Long? = null,
    val meta: Meta? = null
)

data class Links (
    val self: String? = null,
    val html: String? = null,
    val photos: String? = null,
    val likes: String? = null,
    val portfolio: String? = null,
    val following: String? = null,
    val followers: String? = null
)

data class Meta (
    val index: Boolean? = null
)
//
//data class ProfileImage (
//    val small: String? = null,
//    val medium: String? = null,
//    val large: String? = null
//)
//
//data class Social (
//    val instagramUsername: Any? = null,
//    val portfolioURL: Any? = null,
//    val twitterUsername: Any? = null,
//    val paypalEmail: Any? = null
//)

data class Tags (
    val custom: List<Any?>? = null,
    val aggregated: List<Any?>? = null
)
