package com.example.engurutask.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.engurutask.R
import com.example.engurutask.model.WikiModel

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
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
        var listOfPages=wikiModel?.query?.pages

    }

}
