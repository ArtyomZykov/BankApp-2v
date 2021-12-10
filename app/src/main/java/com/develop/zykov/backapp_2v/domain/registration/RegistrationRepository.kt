package com.develop.zykov.backapp_2v.domain.registration

import com.develop.zykov.backapp_2v.data.common.utils.WrappedResponse
import com.develop.zykov.backapp_2v.data.registration.remote.dto.RegistrationRequest
import com.develop.zykov.backapp_2v.data.registration.remote.dto.RegistrationResponse
import kotlinx.coroutines.flow.Flow

interface RegistrationRepository {

    suspend fun registration(registerRequest: RegistrationRequest) : Flow<WrappedResponse<RegistrationResponse>>

}