package com.example.mypinterest.model

data class RelatedPhotos(
    var total: Int? = null,
    var results: ArrayList<Photo>? = null
)