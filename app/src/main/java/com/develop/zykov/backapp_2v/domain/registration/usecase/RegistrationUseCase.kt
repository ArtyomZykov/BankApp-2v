package com.develop.zykov.backapp_2v.domain.registration.usecase

import com.develop.zykov.backapp_2v.data.utils.WrappedResponse
import com.develop.zykov.backapp_2v.data.registration.remote.dto.RegistrationRequest
import com.develop.zykov.backapp_2v.data.registration.remote.dto.RegistrationResponse
import com.develop.zykov.backapp_2v.domain.registration.RegistrationRepository

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegistrationUseCase  @Inject constructor(private val registerRepository: RegistrationRepository) {

    suspend fun invoke(registerRequest: RegistrationRequest) : Flow<WrappedResponse<RegistrationResponse>> {
        return registerRepository.registration(registerRequest)
    }

}