package com.develop.zykov.backapp_2v.domain.loan.usecase

import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanResponse
import com.develop.zykov.backapp_2v.data.utils.WrappedResponse
import com.develop.zykov.backapp_2v.domain.loan.LoanRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoanDataUseCase @Inject constructor(private val loanRepository: LoanRepository) {

    suspend fun invoke(id: Int): Flow<WrappedResponse<LoanResponse>> =
        loanRepository.getLoanData(id)

}