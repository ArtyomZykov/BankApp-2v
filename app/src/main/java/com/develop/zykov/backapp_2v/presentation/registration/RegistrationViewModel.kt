package com.develop.zykov.backapp_2v.presentation.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.zykov.backapp_2v.data.login.remote.dto.LoginRequest
import com.develop.zykov.backapp_2v.data.registration.remote.dto.RegistrationRequest
import com.develop.zykov.backapp_2v.domain.loan.usecase.GetLoanConditionsUseCase
import com.develop.zykov.backapp_2v.domain.login.entity.AuthEntity
import com.develop.zykov.backapp_2v.domain.registration.entity.RegistrationEntity
import com.develop.zykov.backapp_2v.domain.registration.usecase.RegistrationUseCase
import com.develop.zykov.backapp_2v.presentation.login.LoginFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegistrationViewModel @Inject constructor(private val registrationUseCase: RegistrationUseCase) :
    ViewModel() {

    private val state = MutableStateFlow<RegistrationFragmentState>(RegistrationFragmentState.Init)
    val fragmentState: StateFlow<RegistrationFragmentState> get() = state

    private fun setLoading() {
        state.value = RegistrationFragmentState.IsLoading(true)
    }

    private fun hideLoading() {
        state.value = RegistrationFragmentState.IsLoading(false)
    }

    fun registration(regEntity: RegistrationEntity) {
        viewModelScope.launch {
            registrationUseCase.invoke(
                RegistrationRequest(name = regEntity.login, password = regEntity.password)
            )
                .onStart {
                    setLoading()
                }
                .catch {
                    hideLoading()
                }
                .collect { baseResult ->
                    hideLoading()
                    if (baseResult.successful) {
                        Log.d("Login", "OK \t${baseResult.code}\t${baseResult.data}")
                        state.value = RegistrationFragmentState.SuccessLogin(baseResult.data!!.name)
                    } else {
                        Log.d("Login", "Error \t${baseResult.code}\t${baseResult.data}")
                        state.value = RegistrationFragmentState.ErrorLogin(baseResult.code)
                    }
                }
        }
    }

}


sealed class RegistrationFragmentState {
    object Init : RegistrationFragmentState()
    data class IsLoading(val isLoading: Boolean) : RegistrationFragmentState()
    data class SuccessLogin(val name: String) : RegistrationFragmentState()
    data class ErrorLogin(val code: Int) : RegistrationFragmentState()
}