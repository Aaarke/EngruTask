package com.example.engurutask.utility

interface Keys {
    interface ApiField {
        companion object{
            const val REQ_ACTION: String="action"
            const val REQ_FORMAT: String="format"
            const val REQ_GENERATOR: String="generator"
            const val REQ_REDIRECTS: String="redirects"
            const val REQ_FORMAT_VERSION: String="formatversion"
            const val REQ_PIPROP: String="piprop"
            const val REQ_PIT_THUMB_SIZE: String="pithumbsize"
            const val REQ_PILIMIT: String="pilimit"
            const val REQ_WBPTTERMS: String="wbptterms"
            const val REQ_GPS_SEARCH: String="gpssearch"
            const val REQ_GPS_LIMIT: String="gpslimit"
        }
    }

    interface EXTRAS{
        companion object{
            const val REPO_ITEM="repo_item"
        }


    }

    companion object{
        const val BASE_URL = "https://en.wikipedia.org/"
    }
}