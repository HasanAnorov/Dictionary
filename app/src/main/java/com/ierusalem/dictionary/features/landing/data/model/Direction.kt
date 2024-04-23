package com.ierusalem.dictionary.features.home.data.model


import com.google.gson.annotations.SerializedName

data class Direction(
    @SerializedName("created")
    val created: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("updated")
    val updated: String
)