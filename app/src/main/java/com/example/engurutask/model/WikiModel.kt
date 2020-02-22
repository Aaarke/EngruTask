package com.example.engurutask.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WikiModel(
    @SerializedName("batchcomplete")
    @Expose
    val batchcomplete: Boolean? = null,
    @SerializedName("continue")
    @Expose
    val _continue: Continue? = null,
    @SerializedName("query")
    @Expose
    val query:Query
) : Serializable
