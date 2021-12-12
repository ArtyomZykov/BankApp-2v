package com.develop.zykov.backapp_2v.presentation.newloan

import androidx.lifecycle.ViewModel
import com.develop.zykov.backapp_2v.domain.loan.usecase.GetLoanConditionsUseCase
import javax.inject.Inject

class NewLoanViewModel @Inject constructor(private val getLoanConditionsUseCase: GetLoanConditionsUseCase) :
    ViewModel() {

}