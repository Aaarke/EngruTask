package com.example.engurutask.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Terms(
    @SerializedName("description")
    @Expose
    val description: List<String>? = null
) : Serializable