package com.example.funtrip.funtrip.ui.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.funtrip.funtrip.R

class LayoutOne : Fragment() {

    companion object {
        fun newInstance() = LayoutOne()
    }

    private lateinit var viewModel: LayoutOneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.layout_one_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LayoutOneViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
