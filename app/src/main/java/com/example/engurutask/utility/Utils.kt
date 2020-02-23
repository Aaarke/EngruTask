package com.example.engurutask.utility

object Utils {
    fun getUrlFromTitle(title:String): String {

       val str = title.replace("\\s".toRegex(), "_")
        return "https://en.wikipedia.org/wiki/$str"
    }
}