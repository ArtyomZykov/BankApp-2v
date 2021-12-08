package com.develop.zykov.backapp_2v.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.zykov.backapp_2v.data.login.dto.AuthRequest
import com.develop.zykov.backapp_2v.domain.login.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.utf8Size
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase): ViewModel() {


    fun login() {
        viewModelScope.launch {
            val res = loginUseCase.execute(AuthRequest(name = "123", password = "123"))
            Log.d("LogRes", res.body().toString())
        }
    }

}