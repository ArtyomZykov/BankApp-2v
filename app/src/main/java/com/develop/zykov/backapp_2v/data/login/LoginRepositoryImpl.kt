package com.develop.zykov.backapp_2v.data.login

import com.develop.zykov.backapp_2v.data.login.api.LoginApi
import com.develop.zykov.backapp_2v.data.login.dto.AuthRequest
import com.develop.zykov.backapp_2v.domain.login.LoginRepository
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginApi: LoginApi) : LoginRepository {

    override suspend fun login(loginRequest: AuthRequest): Response<String> =
        loginApi.login(loginRequest)

}