package com.example.engurutask.ui.main

import androidx.lifecycle.MutableLiveData
import com.example.engurutask.base.BaseViewModel
import com.example.engurutask.model.WikiModel
import com.example.engurutask.network.ApiInterface
import com.example.engurutask.utility.Keys.ApiField.Companion.REQ_ACTION
import com.example.engurutask.utility.Keys.ApiField.Companion.REQ_FORMAT
import com.example.engurutask.utility.Keys.ApiField.Companion.REQ_FORMAT_VERSION
import com.example.engurutask.utility.Keys.ApiField.Companion.REQ_GENERATOR
import com.example.engurutask.utility.Keys.ApiField.Companion.REQ_GPS_LIMIT
import com.example.engurutask.utility.Keys.ApiField.Companion.REQ_GPS_SEARCH
import com.example.engurutask.utility.Keys.ApiField.Companion.REQ_PILIMIT
import com.example.engurutask.utility.Keys.ApiField.Companion.REQ_PIPROP
import com.example.engurutask.utility.Keys.ApiField.Companion.REQ_PIT_THUMB_SIZE
import com.example.engurutask.utility.Keys.ApiField.Companion.REQ_REDIRECTS
import com.example.engurutask.utility.Keys.ApiField.Companion.REQ_WBPTTERMS
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewViewModel : BaseViewModel() {
    var apiInterface: ApiInterface? = null
        @Inject set
    private val compositeDisposable = CompositeDisposable()
    var resultLiveData: MutableLiveData<WikiModel> = MutableLiveData()
    fun performSearch(query: String) {
        val map = HashMap<String, String>()
        map[REQ_ACTION] = "query"
        map[REQ_FORMAT] = "json"
        map[REQ_GENERATOR] = "prefixsearch"
        map[REQ_REDIRECTS] = "1"
        map[REQ_FORMAT_VERSION] = "2"
        map[REQ_PIPROP] = "thumbnail"
        map[REQ_PIT_THUMB_SIZE] = "50"
        map[REQ_PILIMIT] = "10"
        map[REQ_WBPTTERMS] = "description"
        map["prop"]="pageimages|pageterms"
        map[REQ_GPS_LIMIT] = "10"
        map[REQ_GPS_SEARCH] = query
        apiInterface?.performSearch(map)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnSubscribe {
            }

            ?.subscribe(
                { result ->
                resultLiveData.value=result
                },
                {
                    //homeViewModel.setState(IViewModel.ViewState.ErrorState)
                }
            )?.let { compositeDisposable.add(it) }

    }

    override fun onCleared() {
        super.onCleared()
       compositeDisposable.clear()
    }
}
