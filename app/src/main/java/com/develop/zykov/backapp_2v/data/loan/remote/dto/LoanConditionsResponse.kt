package com.develop.zykov.backapp_2v.data.loan.remote.dto

data class LoanConditionsResponse(
    val maxAmount: Int,
    val percent: Double,
    val period: Int
)
