package com.develop.zykov.backapp_2v.data.registration.di

import com.develop.zykov.backapp_2v.data.common.module.NetworkModule
import com.develop.zykov.backapp_2v.data.registration.remote.api.RegistrationApi
import com.develop.zykov.backapp_2v.data.registration.repository.RegistrationRepositoryImpl
import com.develop.zykov.backapp_2v.domain.registration.RegistrationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class RegistrationModule {

    @Singleton
    @Provides
    fun provideRegisterApi(retrofit: Retrofit) : RegistrationApi {
        return retrofit.create(RegistrationApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRegisterRepository(registerApi: RegistrationApi) : RegistrationRepository {
        return RegistrationRepositoryImpl(registerApi)
    }

}