package com.example.engurutask.network

import androidx.lifecycle.LiveData
import com.example.engurutask.model.WikiModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {

    @GET("/w/api.php")
    fun performSearch(@QueryMap map: HashMap<String, String>): Observable<WikiModel>
}