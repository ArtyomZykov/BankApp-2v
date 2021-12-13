package com.develop.zykov.backapp_2v.presentation.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanResponse
import com.develop.zykov.backapp_2v.domain.loan.usecase.GetLoanDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(private val getLoanDataUseCase: GetLoanDataUseCase) :
    ViewModel() {

    private val state = MutableStateFlow<InfoFragmentState>(InfoFragmentState.Init)
    val fragmentState: StateFlow<InfoFragmentState> get() = state

    private fun setLoading() {
        state.value = InfoFragmentState.IsLoading(true)
    }

    private fun hideLoading() {
        state.value = InfoFragmentState.IsLoading(false)
    }

    fun getData(id: Int) {

        viewModelScope.launch {
            getLoanDataUseCase.invoke(id)
                .onStart {
                    setLoading()
                }
                .catch {
                    hideLoading()
                }
                .collect { baseResult ->
                    hideLoading()
                    if (baseResult.successful) {
                        state.value = InfoFragmentState.SuccessLogin(baseResult.data!!)
                    } else {
                        state.value = InfoFragmentState.ErrorLogin(baseResult.code)
                    }
                }
        }
    }

}

sealed class InfoFragmentState {
    object Init : InfoFragmentState()
    data class IsLoading(val isLoading: Boolean) : InfoFragmentState()
    data class SuccessLogin(val response: LoanResponse) : InfoFragmentState()
    data class ErrorLogin(val code: Int) : InfoFragmentState()
}