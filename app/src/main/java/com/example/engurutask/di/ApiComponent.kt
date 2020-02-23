package com.example.engurutask.di

import com.example.engurutask.ui.main.SearchViewViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ApiComponent {
    @Component.Builder
    interface Builder {
        fun build(): ApiComponent
        fun networkModule(networkModule: NetworkModule): Builder
    }

    fun inject(searchViewViewModel: SearchViewViewModel)

}