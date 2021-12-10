package com.develop.zykov.backapp_2v.data.login.remote.api

import com.develop.zykov.backapp_2v.data.login.remote.dto.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<String>

}