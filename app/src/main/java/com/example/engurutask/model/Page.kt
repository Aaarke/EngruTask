package com.example.engurutask.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Page(
    @SerializedName("pageid")
    @Expose
    val pageId: Int,
    @SerializedName("ns")
    @Expose
    val ns: Int,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("index")
    @Expose
    val index: Int,
    @SerializedName("thumbnail")
    @Expose
    val thumbnail: Thumbnail?=null,
    @SerializedName("terms")
    @Expose
    val terms: Terms?=null

) : Serializable