package com.example.veeapi.responsemodel

data class DataImage(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)