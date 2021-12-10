package com.develop.zykov.backapp_2v.data.loan.di

import com.develop.zykov.backapp_2v.data.loan.remote.api.LoanApi
import com.develop.zykov.backapp_2v.data.loan.repository.LoanRepositoryImpl
import com.develop.zykov.backapp_2v.di.NetworkModule
import com.develop.zykov.backapp_2v.domain.loan.LoanRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class LoanModule {

    @Singleton
    @Provides
    fun provideProductApi(retrofit: Retrofit) : LoanApi {
        return retrofit.create(LoanApi::class.java)
    }

    @Singleton
    @Provides
    fun provideProductRepository(loanApi: LoanApi) : LoanRepository {
        return LoanRepositoryImpl(loanApi)
    }

}