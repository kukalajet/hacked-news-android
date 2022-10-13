package com.jeton.hackednews.core.di

import com.jeton.hackednews.BuildConfig
import com.jeton.hackednews.core.network.model.TypeAdapter
import com.jeton.hackednews.core.network.service.HackerNewsClient
import com.jeton.hackednews.core.network.service.HackerNewsClientImpl
import com.jeton.hackednews.core.network.service.HackerNewsService
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// TODO: Move `BASE_URL` in `BuildConfig`.
const val BASE_URL = "https://hacker-news.firebaseio.com/v0/"

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get<OkHttpClient>(), BASE_URL) }
    single { provideHackerNewsService(get<Retrofit>()) }

    single<HackerNewsClient> {
        return@single HackerNewsClientImpl(get<HackerNewsService>())
    }
}

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()

private fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
    val moshi = Moshi.Builder().add(TypeAdapter()).build()

    return Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()
}

private fun provideHackerNewsService(retrofit: Retrofit): HackerNewsService =
    retrofit.create(HackerNewsService::class.java)