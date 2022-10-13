package com.jeton.hackednews.core.repository

import com.jeton.hackednews.core.network.service.HackerNewsClient

class HackerNewsRepository(private val hackerNewsClient: HackerNewsClient) {

    suspend fun getTopStoriesIds() = hackerNewsClient.fetchTopStoriesIds()

    suspend fun getMaxItemId() = hackerNewsClient.fetchMaxItemId()

    suspend fun getUser(username: String) = hackerNewsClient.fetchUser(username)

    suspend fun getItem(itemId: Int) = hackerNewsClient.fetchItem(itemId)
}