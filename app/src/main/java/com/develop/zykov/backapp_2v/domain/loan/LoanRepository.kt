package com.develop.zykov.backapp_2v.domain.loan

import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanConditionsResponse
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanRequest
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanResponse
import com.develop.zykov.backapp_2v.data.utils.WrappedResponse
import kotlinx.coroutines.flow.Flow

interface LoanRepository {

    suspend fun createLoan(loanRequest: LoanRequest) : Flow<WrappedResponse<LoanResponse>>
    suspend fun getLoanData(id: Int): Flow<WrappedResponse<LoanResponse>>
    suspend fun getLoansData(): Flow<WrappedResponse<List<LoanResponse>>>
    suspend fun getLoanConditions(): Flow<WrappedResponse<LoanConditionsResponse>>

}