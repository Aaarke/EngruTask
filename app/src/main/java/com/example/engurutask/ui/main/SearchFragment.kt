package com.example.engurutask.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engurutask.R
import com.example.engurutask.adapter.WikiSearchAdapter
import com.example.engurutask.model.WikiModel
import com.example.engurutask.utility.Keys
import kotlinx.android.synthetic.main.main_fragment.*

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewViewModel
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var wikiSearchAdapter: WikiSearchAdapter
    private var isAdapterInitialised: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewViewModel::class.java)
        viewModel.performSearch("sachin")
        viewModel.resultLiveData.observe(viewLifecycleOwner, Observer {
            setAdapter(it)
        })

        viewModel.isLoading?.observe(viewLifecycleOwner, Observer { isLoading ->
            run {
                if (isLoading) {
                    pbWiki.visibility = View.VISIBLE
                } else {
                    pbWiki.visibility = View.GONE
                }
            }
        })
    }

    private fun setAdapter(wikiModel: WikiModel?) {
        val listOfPages = wikiModel?.query?.pages
        mLayoutManager = LinearLayoutManager(context)
        rvListOfPages.layoutManager = mLayoutManager
        if (listOfPages != null) {
            if (isAdapterInitialised) {
                wikiSearchAdapter.updateAdapter(listOfPages)
            } else {
                isAdapterInitialised = true
                wikiSearchAdapter =
                    WikiSearchAdapter(activity, listOfPages, object : OnItemClickedListener {
                        override fun onItemClicked(url: String) {
                            openWebView(url)
                        }
                    })
                rvListOfPages.adapter = wikiSearchAdapter
            }
        }

    }

    private fun openWebView(url: String) {
        val intent = Intent(activity, WebViewActivity::class.java)
        intent.putExtra(Keys.EXTRAS.URL, url)
        startActivity(intent)
    }

    fun searchRepo(query: String) {
        rvListOfPages.visibility=View.VISIBLE
        pbWiki.visibility=View.VISIBLE
        ivNoInternet.visibility=View.GONE
        viewModel.performSearch(query)
    }

    fun showNoInternetUi() {
        rvListOfPages.visibility=View.GONE
        pbWiki.visibility=View.GONE
        ivNoInternet.visibility=View.VISIBLE
    }

}
