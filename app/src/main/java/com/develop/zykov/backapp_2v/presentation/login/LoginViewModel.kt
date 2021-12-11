package com.develop.zykov.backapp_2v.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.zykov.backapp_2v.data.login.remote.dto.LoginRequest
import com.develop.zykov.backapp_2v.domain.login.entity.AuthEntity
import com.develop.zykov.backapp_2v.domain.login.usecase.LoginUseCase
import com.develop.zykov.backapp_2v.utils.SharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) :
    ViewModel() {

    @Inject
    lateinit var sharedPrefs: SharedPrefs
    private val state = MutableStateFlow<LoginFragmentState>(LoginFragmentState.Init)
    val nState: StateFlow<LoginFragmentState> get() = state

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

                    when (baseResult.successful) {
                        false -> Log.d(
                            "Login",
                            "Error \t${baseResult.code}\t${baseResult.data}"
                        )
                        true -> {
                            Log.d(
                                "Login",
                                "OK \t${baseResult.code}\t${baseResult.data}"
                            )

                            sharedPrefs.saveToken(baseResult.data.toString())
                        }
                    }

                }
        }
    }

}

sealed class LoginFragmentState {
    object Init : LoginFragmentState()
    data class IsLoading(val isLoading: Boolean) : LoginFragmentState()
}
