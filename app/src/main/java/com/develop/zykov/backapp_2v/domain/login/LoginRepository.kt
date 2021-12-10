package com.develop.zykov.backapp_2v.domain.login

import com.develop.zykov.backapp_2v.data.utils.WrappedResponse
import com.develop.zykov.backapp_2v.data.login.remote.dto.LoginRequest
import kotlinx.coroutines.flow.Flow


interface LoginRepository {

    suspend fun login(loginRequest: LoginRequest) : Flow<WrappedResponse<String>>

}