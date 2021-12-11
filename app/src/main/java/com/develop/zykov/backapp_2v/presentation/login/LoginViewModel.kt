package com.develop.zykov.backapp_2v.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.zykov.backapp_2v.data.login.remote.dto.LoginRequest
import com.develop.zykov.backapp_2v.domain.login.entity.AuthEntity
import com.develop.zykov.backapp_2v.domain.login.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) :
    ViewModel() {

    private val state = MutableStateFlow<LoginFragmentState>(LoginFragmentState.Init)
    val fragmentState: StateFlow<LoginFragmentState> get() = state

    private fun setLoading() {
        state.value = LoginFragmentState.IsLoading(true)
    }

    private fun hideLoading() {
        state.value = LoginFragmentState.IsLoading(false)
    }

    fun login(loginRequest: AuthEntity) {
        viewModelScope.launch {
            loginUseCase.invoke(
                LoginRequest(
                    name = loginRequest.login,
                    password = loginRequest.password
                )
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
                        state.value = LoginFragmentState.SuccessLogin(baseResult.data.toString())
                    } else {
                        Log.d("Login", "Error \t${baseResult.code}\t${baseResult.data}")
                        state.value = LoginFragmentState.ErrorLogin(baseResult.code)
                    }


                }
        }
    }

}

sealed class LoginFragmentState {
    object Init : LoginFragmentState()
    data class IsLoading(val isLoading: Boolean) : LoginFragmentState()
    data class SuccessLogin(val token: String) : LoginFragmentState()
    data class ErrorLogin(val code: Int) : LoginFragmentState()
}
