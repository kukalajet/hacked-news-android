package com.jeton.hackednews.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeton.hackednews.core.network.model.Item
import com.jeton.hackednews.core.repository.HackerNewsRepository
import com.jeton.hackednews.core.utils.asyncMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val hackerNewsRepository: HackerNewsRepository
) : ViewModel() {
    private var topStoriesIds: List<Int>? = null

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>>
        get() = _items

    init {
        setup()
    }

    private fun setup() {
        fetchTopStoriesIds()
    }

    private fun fetchTopStoriesIds() {
        viewModelScope.async {
            withContext(Dispatchers.Default) { hackerNewsRepository.getTopStoriesIds() }.let {
                topStoriesIds = it.body()!!
            }

            fetchItems()
        }
    }

    fun fetchItems(count: Int = 20) {
        if (topStoriesIds == null) return;

        val lowerBoundIndex = items.value.size
        val upperBoundIndex = lowerBoundIndex + count - 1
        if (upperBoundIndex + 1 >= topStoriesIds!!.size) return
        val idsToFetch = topStoriesIds!!.slice(lowerBoundIndex..upperBoundIndex)

        viewModelScope.async {
            idsToFetch.asyncMap { hackerNewsRepository.getItem(it) }.forEach {
                if (it.isSuccessful) {
                    _items.value = items.value + it.body()!!
                }
            }
        }
    }
}