package com.develop.zykov.backapp_2v.data.login.repository

import android.util.Log
import com.develop.zykov.backapp_2v.data.utils.WrappedResponse
import com.develop.zykov.backapp_2v.data.login.remote.api.LoginApi
import com.develop.zykov.backapp_2v.data.login.remote.dto.LoginRequest
import com.develop.zykov.backapp_2v.domain.login.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(private val loginApi: LoginApi) : LoginRepository {

    override suspend fun login(loginRequest: LoginRequest): Flow<WrappedResponse<String>> =
        flow {
            val response = loginApi.login(loginRequest)
            if (response.isSuccessful) {
                emit(
                    WrappedResponse(
                        code = response.code(),
                        data = response.body(),
                        successful = true
                    )
                )
            } else {
                emit(
                    WrappedResponse(
                        code = response.code(),
                        data = response.body(),
                        successful = false
                    )
                )
            }
        }

}