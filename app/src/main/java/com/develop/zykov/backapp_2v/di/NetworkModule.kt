package com.develop.zykov.backapp_2v.di

import com.develop.zykov.backapp_2v.utils.SharedPrefs
import com.develop.zykov.backapp_2v.data.utils.RequestInterceptor
import com.develop.zykov.backapp_2v.utils.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import retrofit2.converter.scalars.ScalarsConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {

            addConverterFactory(ScalarsConverterFactory.create())

            addConverterFactory(GsonConverterFactory.create())

            client(okHttp)
            baseUrl(Config.API_BASE_URL)
        }.build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(requestInterceptor: RequestInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(requestInterceptor)
        }.build()
    }

    @Provides
    fun provideRequestInterceptor(prefs: SharedPrefs): RequestInterceptor {
        return RequestInterceptor(prefs)
    }

}