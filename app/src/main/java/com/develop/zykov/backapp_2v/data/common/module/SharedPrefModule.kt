package com.develop.zykov.backapp_2v.data.common.module

import android.content.Context
import com.develop.zykov.backapp_2v.utils.SharedPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefModule {

    @Provides
    fun provideSharedPref(@ApplicationContext context: Context) : SharedPrefs {
        return SharedPrefs(context)
    }

}