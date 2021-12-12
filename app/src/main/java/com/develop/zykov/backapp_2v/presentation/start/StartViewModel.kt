package com.develop.zykov.backapp_2v.presentation.start

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanResponse
import com.develop.zykov.backapp_2v.domain.loan.usecase.GetLoansDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(private val getLoansDataUseCase: GetLoansDataUseCase) :
    ViewModel() {

    private val state = MutableStateFlow<StartFragmentState>(StartFragmentState.Init)
    val fragmentState: StateFlow<StartFragmentState> get() = state


    private fun setLoading() {
        state.value = StartFragmentState.IsLoading(true)
    }

    private fun hideLoading() {
        state.value = StartFragmentState.IsLoading(false)
    }


    fun getUserLoans() {

        viewModelScope.launch {
            getLoansDataUseCase.invoke()
                .onStart {
                    setLoading()
                }
                .catch {
                    hideLoading()
                }
                .collect { baseResult ->
                    hideLoading()
                    if (baseResult.successful) {
                        state.value = StartFragmentState.SuccessResponse(baseResult.data!!)
                    } else {
                        state.value = StartFragmentState.ErrorLogin(baseResult.code)
                    }
                }
        }

    }

}

sealed class StartFragmentState {
    object Init : StartFragmentState()
    data class IsLoading(val isLoading: Boolean) : StartFragmentState()
    data class ErrorLogin(val code: Int) : StartFragmentState()
    data class SuccessResponse(val response : List<LoanResponse>) : StartFragmentState()
}