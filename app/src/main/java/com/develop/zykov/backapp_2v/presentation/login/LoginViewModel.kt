package com.develop.zykov.backapp_2v.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.zykov.backapp_2v.data.login.dto.AuthRequest
import com.develop.zykov.backapp_2v.domain.login.usecase.LoginUseCase
import com.develop.zykov.backapp_2v.utils.SharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    fun login(loginRequest: AuthRequest) {

        sharedPrefs.clear()

        viewModelScope.launch {
            loginUseCase.execute(loginRequest)
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
                            "Error " + baseResult.code.toString()
                        )
                        else ->  {
                            Log.d("Login", "Success " + baseResult.code + "\n" + baseResult.data)
                            sharedPrefs.saveToken(baseResult.data.toString())
                        }
                    }
                }
        }
    }

}
