package com.develop.zykov.backapp_2v.domain.login.usecase


import com.develop.zykov.backapp_2v.data.login.dto.AuthRequest
import com.develop.zykov.backapp_2v.domain.login.LoginRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend fun execute(loginRequest: AuthRequest): Response<String> {
        return loginRepository.login(loginRequest)
    }

}