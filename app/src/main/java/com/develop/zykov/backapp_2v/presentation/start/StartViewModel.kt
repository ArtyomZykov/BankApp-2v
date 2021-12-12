package com.develop.zykov.backapp_2v.presentation.start

import androidx.lifecycle.ViewModel
import com.develop.zykov.backapp_2v.domain.loan.usecase.GetLoansDataUseCase
import com.develop.zykov.backapp_2v.domain.registration.usecase.RegistrationUseCase
import javax.inject.Inject

class StartViewModel @Inject constructor(private val getLoansDataUseCase: GetLoansDataUseCase) :
    ViewModel() {

}