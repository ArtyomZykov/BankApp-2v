package com.develop.zykov.backapp_2v.domain.login

import com.develop.zykov.backapp_2v.data.common.utils.WrappedResponse
import com.develop.zykov.backapp_2v.data.login.dto.AuthRequest
import kotlinx.coroutines.flow.Flow


interface LoginRepository {

    suspend fun login(loginRequest: AuthRequest) : Flow<WrappedResponse<String>>
            //Flow<BaseResult<String, WrappedResponse<String>>>
}