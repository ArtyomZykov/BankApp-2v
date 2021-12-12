package com.develop.zykov.backapp_2v.data.loan.remote.dto

import java.util.*

data class LoanResponse(
    val amount: String,
    val date: Date,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val percent: String,
    val period: String,
    val phoneNumber: String,
    val state: String
)
