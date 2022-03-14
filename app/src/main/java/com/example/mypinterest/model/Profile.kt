package com.example.mypinterest.model


data class Profile (
    val id: String? = null,
    val updated_at : String? = null,
    val username : String? = null,
    val name : String? = null,
    val first_name : String? = null,
    val last_name : String? = null,
    val twitter_username : String? = null,
    val portfolio_url : String? = null,
    val bio : String? = null,
    val location : String? = null,
    val links : Links? = null,
    val profile_image : ProfileImage? = null,
    val instagram_username : String? = null,
    val total_collections : Int? = null,
    val total_likes : Int? = null,
    val total_photos : Int? = null,
    val accepted_tos : Boolean? = null,
    val for_hire : Boolean? = null,
    val social : Social? = null,
    val followed_by_user : Boolean? = null,
    val photos : List<String>? = null,
    val badge : String? = null,
    val tags : Tags? = null,
    val followers_count : Int? = null,
    val following_count : Int? = null,
    val allow_messages : Boolean? = null,
    val numeric_id : Int? = null,
    val downloads : Int? = null,
    val meta : Meta? = null
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
