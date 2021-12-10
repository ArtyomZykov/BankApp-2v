package com.develop.zykov.backapp_2v.data.registration.repository

import com.develop.zykov.backapp_2v.data.common.utils.WrappedResponse
import com.develop.zykov.backapp_2v.data.registration.remote.api.RegistrationApi
import com.develop.zykov.backapp_2v.data.registration.remote.dto.RegistrationRequest
import com.develop.zykov.backapp_2v.data.registration.remote.dto.RegistrationResponse
import com.develop.zykov.backapp_2v.domain.registration.RegistrationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(private val registrationApi: RegistrationApi) :
    RegistrationRepository {

    override suspend fun registration(registerRequest: RegistrationRequest): Flow<WrappedResponse<RegistrationResponse>> =
        flow {
            val response = registrationApi.registration(registerRequest)
            if (response.isSuccessful) {
                emit(WrappedResponse(code = response.code(), data = response.body()))
            } else {
                emit(WrappedResponse(code = response.code(), data = response.body()))
            }
        }

}