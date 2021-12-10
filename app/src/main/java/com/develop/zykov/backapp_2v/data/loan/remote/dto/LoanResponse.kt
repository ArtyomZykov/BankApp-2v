package com.develop.zykov.backapp_2v.data.loan.remote.dto

data class LoanResponse(
    val amount: String,
    val date: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val percent: String,
    val period: String,
    val phoneNumber: String,
    val state: String
)
