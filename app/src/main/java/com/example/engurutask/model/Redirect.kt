package com.example.engurutask.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Redirect(
    @SerializedName("index")
    @Expose
    val index: Int,
    @SerializedName("from")
    @Expose
    val from: String,
    @SerializedName("to")
    @Expose
    val to: String
) : Serializable