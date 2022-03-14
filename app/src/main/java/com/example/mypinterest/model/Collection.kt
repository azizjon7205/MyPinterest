package com.example.mypinterest.model

typealias Collections = ArrayList<Collection>

data class Collection(
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val published_at: String? = null,
    val last_collected_at: String? = null,
    val updated_at: String? = null,
    val curated: Boolean? = null,
    val featured: Boolean? = null,
    val total_photos: Long? = null,
    val private: Boolean? = null,
    val share_key: String? = null,
    val tags: List<Tag>? = null,
    val links: CollectionLinks? = null,
    val user: User? = null,
    val cover_photo: Photo? = null,
    val preview_photos: List<PreviewPhoto>? = null
)

data class Tag (
    val type: Type? = null,
    val title: String? = null,
    val source: Source? = null
)

data class Source (
    val ancestry: Ancestry? = null,
    val title: String? = null,
    val subtitle: String? = null,
    val description: String? = null,
    val metaTitle: String? = null,
    val metaDescription: String? = null,
    val cover_photo: Photo? = null
)

data class Ancestry (
    val type: Category? = null,
    val category: Category? = null,
    val subcategory: Category? = null
)

data class Category (
    val slug: String? = null,
    val pretty_slug: String? = null
)

enum class Type {
    LandingPage,
    Search
}

data class CollectionLinks(
    val self: String,
    val html: String,
    val photos: String,
    val related: String,
)
