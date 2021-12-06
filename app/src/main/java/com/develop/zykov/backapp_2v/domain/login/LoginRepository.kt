package com.develop.zykov.backapp_2v.domain.login

import com.develop.zykov.backapp_2v.data.login.dto.AuthRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface LoginRepository {

    suspend fun login(loginRequest: AuthRequest) : Response<String>
}