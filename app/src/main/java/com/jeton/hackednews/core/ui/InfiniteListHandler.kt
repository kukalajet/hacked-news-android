package com.jeton.hackednews.core.ui

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Handler to make any lazy column (or lazy row) infinite. Will notify the [onLoadMore] callback
 * once needed.
 * @param state state of the list that needs to also be passed to the `LazyColumn` composable. Get
 * it by calling `rememberLazyListState()`.
 * @param buffer the number of items before the end of the list to call the [onLoadMore] callback.
 * @param onLoadMore will notify when we need to load more.
 *
 * Source: "https://dev.to/luismierez/infinite-lazycolumn-in-jetpack-compose-44a4"
 */
@Composable
fun InfiniteListHandler(
    state: LazyListState,
    buffer: Int = 2,
    onLoadMore: () -> Unit
) {
    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = state.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1

            lastVisibleItemIndex > (totalItemsNumber - buffer)
        }
    }

    LaunchedEffect(loadMore) {
        snapshotFlow { loadMore.value }
            .distinctUntilChanged()
            .collect {
                onLoadMore()
            }
    }
}