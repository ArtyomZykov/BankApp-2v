package com.develop.zykov.backapp_2v.data.login.di

import com.develop.zykov.backapp_2v.data.common.module.NetworkModule
import com.develop.zykov.backapp_2v.data.login.LoginRepositoryImpl
import com.develop.zykov.backapp_2v.data.login.api.LoginApi
import com.develop.zykov.backapp_2v.domain.login.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class LoginModule {

    @Singleton
    @Provides
    fun provideLoginApi(retrofit: Retrofit) : LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLoginRepository(loginApi: LoginApi) : LoginRepository {
        return LoginRepositoryImpl(loginApi)
    }


}