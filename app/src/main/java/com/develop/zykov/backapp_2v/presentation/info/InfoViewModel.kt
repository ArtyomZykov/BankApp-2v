package com.develop.zykov.backapp_2v.presentation.info

import androidx.lifecycle.ViewModel
import com.develop.zykov.backapp_2v.domain.loan.usecase.GetLoanDataUseCase
import javax.inject.Inject

class InfoViewModel @Inject constructor(private val getLoanDataUseCase: GetLoanDataUseCase) :
    ViewModel() {

}