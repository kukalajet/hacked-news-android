package com.jeton.hackednews.core.di

import com.jeton.hackednews.core.network.service.HackerNewsClient
import com.jeton.hackednews.core.repository.HackerNewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        HackerNewsRepository(get<HackerNewsClient>())
    }
}