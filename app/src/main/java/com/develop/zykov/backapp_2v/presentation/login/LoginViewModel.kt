package com.develop.zykov.backapp_2v.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.zykov.backapp_2v.data.login.remote.dto.LoginRequest
import com.develop.zykov.backapp_2v.data.registration.remote.dto.RegistrationRequest
import com.develop.zykov.backapp_2v.domain.login.usecase.LoginUseCase
import com.develop.zykov.backapp_2v.domain.registration.usecase.RegistrationUseCase
import com.develop.zykov.backapp_2v.utils.SharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: RegistrationUseCase) : ViewModel() {

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    fun login(loginRequest: LoginRequest) {
        TODO("Not yet realized")
        sharedPrefs.clear()

        viewModelScope.launch {
            loginUseCase.execute(RegistrationRequest(name = "4048", password = "4325"))
                .onStart {
                    // setLoading()
                }
                .catch { exception ->
                    // hideLoading()
                    Log.d("Login", "Catch " + exception.message.toString())
                }
                .collect { baseResult ->
                    //hideLoading()
                    when (baseResult.data) {
                        null -> Log.d(
                            "Login",
                            "Error \t${baseResult.code}\t${baseResult.data}"
                        )
                        else ->  {
                            Log.d("Login", "Success \t${baseResult.code}\t${baseResult.data}")
                            sharedPrefs.saveToken(baseResult.data.toString())
                        }
                    }
                }
        }
    }

}
