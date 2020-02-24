package com.example.engurutask.ui.main

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.example.engurutask.R
import com.example.engurutask.base.BaseActivity
import com.example.engurutask.utility.Constants
import com.example.engurutask.utility.GlobalVars


class SearchActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SearchFragment.newInstance(),Constants.Fragment.FRAG_SEARCH)
                    .commitNow()
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dashboard, menu)
        val myActionMenuItem = menu?.findItem(R.id.action_search)
        val searchView = myActionMenuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                myActionMenuItem.collapseActionView()

                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (GlobalVars.isNetworkConnected){
                    val frag =
                        supportFragmentManager.findFragmentByTag(Constants.Fragment.FRAG_SEARCH) as SearchFragment
                    frag.searchRepo(query)
                }else{
                    makeToast()
                    showNoInternetUi()

                }

                return false
            }
        })
        return true
    }

    private fun showNoInternetUi() {
        val frag =
            supportFragmentManager.findFragmentByTag(Constants.Fragment.FRAG_SEARCH) as SearchFragment
        frag.showNoInternetUi()
    }


}
