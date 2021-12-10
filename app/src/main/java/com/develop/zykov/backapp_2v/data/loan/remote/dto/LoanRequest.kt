package com.develop.zykov.backapp_2v.data.loan.remote.dto

data class LoanRequest(
    val amount:	Int,
    val firstName: String,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String
)
