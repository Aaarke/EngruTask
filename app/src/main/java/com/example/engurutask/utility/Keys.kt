package com.example.engurutask.utility

interface Keys {
    interface ApiField {
        companion object{
            const val REQ_Q: String="q"


        }
    }

    interface EXTRAS{
        companion object{
            const val REPO_ITEM="repo_item"
        }


    }

    companion object{
        const val BASE_URL = "https://en.wikipedia.org"
    }
}