package com.develop.zykov.backapp_2v.data.utils

import com.google.gson.annotations.SerializedName

data class WrappedListResponse<T> (
    var code: Int,
    @SerializedName("data") var data : List<T>? = null
)

data class WrappedResponse<T> (
    var code: Int,
    var successful: Boolean,
    var data : T? = null
)