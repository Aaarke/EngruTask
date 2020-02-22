package com.example.engurutask.network

import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {

    @GET("/search/repositories")
    fun performSearch(@QueryMap map: HashMap<String, String>?): LiveData<Any>
}