package com.develop.zykov.backapp_2v.domain.login.usecase


import com.develop.zykov.backapp_2v.data.utils.WrappedResponse
import com.develop.zykov.backapp_2v.data.login.remote.dto.LoginRequest
import com.develop.zykov.backapp_2v.domain.login.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend fun invoke(loginRequest: LoginRequest): Flow<WrappedResponse<String>> {
        return loginRepository.login(loginRequest)
    }

}