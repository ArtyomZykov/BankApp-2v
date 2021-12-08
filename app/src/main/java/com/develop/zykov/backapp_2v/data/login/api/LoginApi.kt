package com.develop.zykov.backapp_2v.data.login.api

import com.develop.zykov.backapp_2v.data.login.dto.AuthRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApi {
    @POST("login")
    suspend fun login(@Body loginRequest: AuthRequest) : Response<String>
}