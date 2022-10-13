package com.jeton.hackednews.core.di

import com.jeton.hackednews.core.repository.HackerNewsRepository
import com.jeton.hackednews.feature.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(get<HackerNewsRepository>())
    }
}