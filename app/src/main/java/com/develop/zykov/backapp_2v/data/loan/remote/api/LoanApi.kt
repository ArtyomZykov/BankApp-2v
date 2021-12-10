package com.develop.zykov.backapp_2v.data.loan.remote.api

import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanConditionsResponse
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanRequest
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoanApi {

    @POST("loans")
    suspend fun createLoan(@Body loginRequest: LoanRequest) : Response<LoanResponse>

    @GET("loans/{id}")
    suspend fun getLoanData(@Path("id") id: Int): Response<LoanResponse>

    @GET("loans/all")
    suspend fun getLoansData(): Response<List<LoanResponse>>

    @GET("loans/conditions")
    suspend fun getLoanConditions(): Response<LoanConditionsResponse>

}