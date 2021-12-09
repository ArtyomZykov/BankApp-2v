package com.develop.zykov.backapp_2v.data.login

import com.develop.zykov.backapp_2v.data.common.utils.WrappedResponse
import com.develop.zykov.backapp_2v.data.login.api.LoginApi
import com.develop.zykov.backapp_2v.data.login.dto.AuthRequest
import com.develop.zykov.backapp_2v.domain.login.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(private val loginApi: LoginApi) : LoginRepository {



    override suspend fun login(loginRequest: AuthRequest): Flow<WrappedResponse<String>> =
        flow {
            val response = loginApi.login(loginRequest)
            if (response.isSuccessful) {
                emit(WrappedResponse(code = response.code(), data = response.body()))
            } else {
                emit(WrappedResponse(code = response.code(), data = response.body()))
            }
        }


/*
    override suspend fun login(loginRequest: AuthRequest): Response<String> =
        loginApi.login(loginRequest)

 */



}