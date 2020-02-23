package com.example.engurutask.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Query(
    @SerializedName("redirects")
    @Expose
    val redirects: List<Redirect>? = null,
    @SerializedName("pages")
    @Expose
    val pages: ArrayList<Page>? = null
) : Serializable