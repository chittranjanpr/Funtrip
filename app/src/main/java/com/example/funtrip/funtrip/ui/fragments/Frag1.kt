package com.example.funtrip.funtrip.ui.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.funtrip.funtrip.R
import com.example.funtrip.funtrip.ui.fragments.LayoutOneViewModel

class Frag1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frag1)
    }
}



//class Frag1 : Fragment() {
//
//    companion object {
//        fun newInstance() = Frag1()
//    }
//
//    private lateinit var viewModel: LayoutOneViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return inflater.inflate(R.layout.activity_frag1, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(LayoutOneViewModel::class.java)
//        // TODO: Use the ViewModel
//    }
//
//}
