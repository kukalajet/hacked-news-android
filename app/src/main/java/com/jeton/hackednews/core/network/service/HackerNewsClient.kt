package com.jeton.hackednews.core.network.service

import com.jeton.hackednews.core.network.model.Item
import com.jeton.hackednews.core.network.model.User
import retrofit2.Response

interface HackerNewsClient {

    suspend fun fetchTopStoriesIds(): Response<List<Int>>

    suspend fun fetchMaxItemId(): Response<Int>

    suspend fun fetchUser(username: String): Response<User>

    suspend fun fetchItem(itemId: Int): Response<Item>
}