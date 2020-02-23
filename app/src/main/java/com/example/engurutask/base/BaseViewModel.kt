package com.example.engurutask.base

import androidx.lifecycle.ViewModel
import com.example.engurutask.di.ApiComponent
import com.example.engurutask.di.DaggerApiComponent
import com.example.engurutask.di.NetworkModule
import com.example.engurutask.ui.main.SearchViewViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: ApiComponent = DaggerApiComponent
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is SearchViewViewModel -> {
                injector.inject(this)
            }


        }
    }
}