package com.develop.zykov.backapp_2v.data.registration.remote.api

import com.develop.zykov.backapp_2v.data.login.remote.dto.LoginRequest
import com.develop.zykov.backapp_2v.data.registration.remote.dto.RegistrationRequest
import com.develop.zykov.backapp_2v.data.registration.remote.dto.RegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationApi {

    @POST("/registration")
    suspend fun registration(@Body registerRequest: RegistrationRequest) : Response<RegistrationResponse>

}