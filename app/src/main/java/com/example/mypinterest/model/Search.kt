package com.example.mypinterest.model

data class Search (
    val total: Long?,
    val totalPages: Long?,
    val results: List<Photo?>
)