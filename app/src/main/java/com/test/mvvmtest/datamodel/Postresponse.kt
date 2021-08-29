package com.test.mvvmtest.datamodel

data class Postresponse(
    val page: Int,
    val posts: List<PostX>
)

data class PostX(
    val event_date: Int,
    val event_name: String,
    val id: String,
    val likes: Int,
    val shares: Int,
    val thumbnail_image: String,
    val views: Int
)