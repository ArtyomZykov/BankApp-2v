package com.develop.zykov.backapp_2v.data.common.module

import com.develop.zykov.backapp_2v.utils.SharedPrefs
import com.develop.zykov.backapp_2v.data.common.utils.RequestInterceptor
import com.google.gson.stream.JsonReader
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
import com.google.gson.GsonBuilder

import com.google.gson.Gson


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
            baseUrl("https://focusstart.appspot.com/")
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