package com.example.mypinterest.model

typealias Topics = ArrayList<Topic>

data class Topic (
    val id: String? = null,
    val slug: String? = null,
    val title: String? = null,
    val description: String? = null,
    val published_at: String? = null,
    val updated_at: String? = null,
    val starts_at: String? = null,
    val ends_at: String? = null,
    val only_submissions_after: Any? = null,
    val featured: Boolean? = null,
    val total_photos: Long? = null,
    val current_user_contributions: List<Any?>? = null,
    val total_current_user_submissions: Any? = null,
    val links: TopicLinks? = null,
    val status: String? = null,
    val owners: List<User>? = null,
    val cover_photo: Photo? = null,
    val preview_photos: List<PreviewPhoto>? = null
)


data class CoverPhotoLinks (
    val self: String? = null,
    val html: String? = null,
    val download: String? = null,
    val downloadLocation: String? = null
)

data class TopicSubmissions (
    val actForNature: The3_DRenders? = null,
    val nature: The3_DRenders? = null,
    val entrepreneur: The3_DRenders? = null,
    val artsCulture: ArtsCulture? = null,
    val businessWork: The3_DRenders? = null,
    val currentEvents: The3_DRenders? = null,
    val spirituality: ArtsCulture? = null,
    val travel: ArtsCulture? = null,
    val texturesPatterns: The3_DRenders? = null,
    val wallpapers: The3_DRenders? = null,
    val experimental: ArtsCulture? = null,
    val the3DRenders: The3_DRenders? = null
)

data class The3_DRenders (
    val status: Status? = null,
    val approvedOn: String? = null
)

enum class Status {
    Approved,
    Rejected
}


data class TopicLinks (
    val self: String? = null,
    val html: String? = null,
    val photos: String? = null
)

data class PreviewPhoto (
    val id: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val blurHash: String? = null,
    val urls: Urls? = null
)
