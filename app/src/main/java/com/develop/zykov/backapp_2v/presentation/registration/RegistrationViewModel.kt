package com.develop.zykov.backapp_2v.presentation.registration

import androidx.lifecycle.ViewModel
import com.develop.zykov.backapp_2v.domain.loan.usecase.GetLoanConditionsUseCase
import com.develop.zykov.backapp_2v.domain.registration.usecase.RegistrationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RegistrationViewModel @Inject constructor(private val registrationUseCase: RegistrationUseCase) :
    ViewModel() {


}