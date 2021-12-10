package com.develop.zykov.backapp_2v.data.loan.repository

import com.develop.zykov.backapp_2v.data.loan.remote.api.LoanApi
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanConditionsResponse
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanRequest
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanResponse
import com.develop.zykov.backapp_2v.data.utils.WrappedResponse
import com.develop.zykov.backapp_2v.domain.loan.LoanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoanRepositoryImpl @Inject constructor(private val loanApi: LoanApi) : LoanRepository {

    override suspend fun createLoan(loanRequest: LoanRequest): Flow<WrappedResponse<LoanResponse>> =
        flow {
            val response = loanApi.createLoan(loanRequest)
            if (response.isSuccessful) {
                emit(WrappedResponse(code = response.code(), data = response.body(), successful = true))
            } else {
                emit(WrappedResponse(code = response.code(), data = response.body(), successful = false))
            }
        }

    override suspend fun getLoanData(id: Int): Flow<WrappedResponse<LoanResponse>> =
        flow {
            val response = loanApi.getLoanData(id)
            if (response.isSuccessful) {
                emit(WrappedResponse(code = response.code(), data = response.body(), successful = true))
            } else {
                emit(WrappedResponse(code = response.code(), data = response.body(), successful = false))
            }
        }

    override suspend fun getLoansData(): Flow<WrappedResponse<List<LoanResponse>>> =
        flow {
            val response = loanApi.getLoansData()
            if (response.isSuccessful) {
                emit(WrappedResponse(code = response.code(), data = response.body(), successful = true))
            } else {
                emit(WrappedResponse(code = response.code(), data = response.body(), successful = false))
            }
        }

    override suspend fun getLoanConditions(): Flow<WrappedResponse<LoanConditionsResponse>> =
        flow {
            val response = loanApi.getLoanConditions()
            if (response.isSuccessful) {
                emit(WrappedResponse(code = response.code(), data = response.body(), successful = true))
            } else {
                emit(WrappedResponse(code = response.code(), data = response.body(), successful = false))
            }
        }

}