package com.jeton.hackednews.core.network.service

class HackerNewsClientImpl(
    private val hackerNewsService: HackerNewsService
) : HackerNewsClient {

    override suspend fun fetchTopStoriesIds() = hackerNewsService.fetchTopStoriesIds()

    override suspend fun fetchMaxItemId() = hackerNewsService.fetchMaxItemId()

    override suspend fun fetchUser(username: String) = hackerNewsService.fetchUser(username)

    override suspend fun fetchItem(itemId: Int) = hackerNewsService.fetchItem(itemId)
}