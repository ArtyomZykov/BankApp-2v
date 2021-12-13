package com.develop.zykov.backapp_2v.presentation.newloan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanConditionsResponse
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanRequest
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanResponse
import com.develop.zykov.backapp_2v.domain.loan.usecase.CreateLoanUseCase
import com.develop.zykov.backapp_2v.domain.loan.usecase.GetLoanConditionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewLoanViewModel @Inject constructor(
    private val createLoanUseCase: CreateLoanUseCase,
    private val getLoanConditionsUseCase: GetLoanConditionsUseCase
) :
    ViewModel() {

    private val state = MutableStateFlow<NewLoanFragmentState>(NewLoanFragmentState.Init)
    val fragmentState: StateFlow<NewLoanFragmentState> get() = state

    private fun setLoading() {
        state.value = NewLoanFragmentState.IsLoading(true)
    }

    private fun hideLoading() {
        state.value = NewLoanFragmentState.IsLoading(false)
    }

    fun createLoan(loanRequest: LoanRequest) {

        viewModelScope.launch {
            createLoanUseCase.invoke(loanRequest)
                .onStart {
                    setLoading()
                }
                .catch {
                    hideLoading()
                }
                .collect { baseResult ->
                    hideLoading()
                    if (baseResult.successful) {
                        state.value = NewLoanFragmentState.SuccessCreateLoan(baseResult.data!!)
                    } else {
                        state.value = NewLoanFragmentState.ErrorCreateLoan(baseResult.code)
                    }
                }
        }
    }

    fun getLoanConditions() {

        viewModelScope.launch {
            getLoanConditionsUseCase.invoke()
                .onStart {
                    setLoading()
                }
                .catch {
                    hideLoading()
                }
                .collect { baseResult ->
                    hideLoading()
                    if (baseResult.successful) {
                        state.value = NewLoanFragmentState.SuccessGetLoanConditions(baseResult.data!!)
                    } else {
                        state.value = NewLoanFragmentState.ErrorGetLoanConditions(baseResult.code)
                    }
                }
        }
    }

}

sealed class NewLoanFragmentState {
    object Init : NewLoanFragmentState()
    data class IsLoading(val isLoading: Boolean) : NewLoanFragmentState()

    data class SuccessGetLoanConditions(val response: LoanConditionsResponse) : NewLoanFragmentState()
    data class ErrorGetLoanConditions(val code: Int) : NewLoanFragmentState()

    data class SuccessCreateLoan(val response: LoanResponse) : NewLoanFragmentState()
    data class ErrorCreateLoan(val code: Int) : NewLoanFragmentState()
}