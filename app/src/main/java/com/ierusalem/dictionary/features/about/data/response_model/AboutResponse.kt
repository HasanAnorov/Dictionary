package com.ierusalem.dictionary.features.about.data.response_model


import com.google.gson.annotations.SerializedName

data class AboutResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean
)