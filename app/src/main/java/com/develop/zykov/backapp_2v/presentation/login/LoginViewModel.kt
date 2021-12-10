package com.develop.zykov.backapp_2v.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanRequest
import com.develop.zykov.backapp_2v.data.login.remote.dto.LoginRequest
import com.develop.zykov.backapp_2v.data.registration.remote.dto.RegistrationRequest
import com.develop.zykov.backapp_2v.domain.loan.usecase.CreateLoanUseCase
import com.develop.zykov.backapp_2v.domain.loan.usecase.GetLoanConditionsUseCase
import com.develop.zykov.backapp_2v.domain.loan.usecase.GetLoanDataUseCase
import com.develop.zykov.backapp_2v.domain.loan.usecase.GetLoansDataUseCase
import com.develop.zykov.backapp_2v.domain.login.usecase.LoginUseCase
import com.develop.zykov.backapp_2v.domain.registration.usecase.RegistrationUseCase
import com.develop.zykov.backapp_2v.utils.SharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val getLoanConditionsUseCase: GetLoanConditionsUseCase) :
    ViewModel() {

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    fun login(loginRequest: LoginRequest) {
        sharedPrefs.clear()
        sharedPrefs.saveToken("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJaeWtvdjIiLCJleHAiOjE2NDE3MzkyNTV9.WhmJvM7PjHc1o0VH9w-G-ejCn5CTi-N297lUvIe2rcBNJlCmCKQSBgkZBNHpVPNH9w-S4T1RbBuOheorPxY1Cw")

        viewModelScope.launch {
            getLoanConditionsUseCase.invoke()
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
                        else -> {
                            Log.d("Login", "Success \t${baseResult.code}\t${baseResult.data}")
                            sharedPrefs.saveToken(baseResult.data.toString())
                        }
                    }
                }
        }
    }

}
