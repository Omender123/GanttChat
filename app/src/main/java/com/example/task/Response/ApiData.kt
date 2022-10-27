package com.example.task.Response

import com.google.gson.annotations.SerializedName

data class ApiData(
    @SerializedName("success") var success: String,
    @SerializedName("message") var message: String
)


