package com.example.veeapi.responsemodel

data class Hit(
    val id: Int,
    val largeImageURL: String,
    val likes: Int,
    val previewURL: String,
    val tags: String,
    val user: String,
    val userImageURL: String,
    val user_id: Int,
    val views: Int,

)