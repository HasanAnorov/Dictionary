package com.ierusalem.dictionary.features.about.data.response_model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("content")
    val content: String
)