package com.example.engurutask.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Continue(
    @SerializedName("gpsoffset")
    @Expose
    val gpsOffset: Int,
    @SerializedName("continue")
    @Expose
    val _continue: String
):Serializable