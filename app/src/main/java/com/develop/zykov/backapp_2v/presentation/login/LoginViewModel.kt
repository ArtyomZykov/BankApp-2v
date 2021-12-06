package com.develop.zykov.backapp_2v.presentation.login

import androidx.lifecycle.ViewModel
import com.develop.zykov.backapp_2v.domain.login.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase): ViewModel() {

}