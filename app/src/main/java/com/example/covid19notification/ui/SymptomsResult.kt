package com.example.covid19notification.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.covid19notification.R

class SymptomsResult : Fragment() {

    companion object {
        fun newInstance() = SymptomsResult()
    }

    private lateinit var viewModel: SymptomsResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.symptoms_result_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SymptomsResultViewModel::class.java)
        // TODO: Use the ViewModel
    }

}