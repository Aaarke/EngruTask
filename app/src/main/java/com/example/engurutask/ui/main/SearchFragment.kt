package com.example.engurutask.ui.main

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
                wikiSearchAdapter = WikiSearchAdapter(activity, listOfPages)
                rvListOfPages.adapter = wikiSearchAdapter
            }
        }

    }

    fun searchRepo(query: String) {
        viewModel.performSearch(query)
    }

}
