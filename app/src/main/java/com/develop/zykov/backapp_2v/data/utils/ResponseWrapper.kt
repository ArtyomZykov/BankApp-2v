package com.develop.zykov.backapp_2v.data.utils


data class WrappedResponse<T> (
    var code: Int,
    var successful: Boolean,
    var data : T? = null
)