package com.jeton.hackednews.feature.home.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import com.jeton.hackednews.core.network.model.Item
import com.jeton.hackednews.core.ui.InfiniteListHandler

@Composable
fun HomeList(
    items: List<Item>,
    onLoadMore: () -> Unit
) {
    val state = rememberLazyListState()

    InfiniteListHandler(state = state) {
        onLoadMore()
    }

    LazyColumn(state = state) {
        itemsIndexed(items) { index, it ->
            ListItem(it, index)
        }
    }
}