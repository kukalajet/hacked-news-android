package com.jeton.hackednews.core.network.service

import com.jeton.hackednews.core.network.model.Item
import com.jeton.hackednews.core.network.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsService {

    @GET("topstories.json")
    suspend fun fetchTopStoriesIds(): Response<List<Int>>

    @GET("maxitem.json")
    suspend fun fetchMaxItemId(): Response<Int>

    @GET("user/{username}.json")
    suspend fun fetchUser(@Path("username") username: String): Response<User>

    @GET("item/{id}.json")
    suspend fun fetchItem(@Path("id") itemId: Int): Response<Item>
}