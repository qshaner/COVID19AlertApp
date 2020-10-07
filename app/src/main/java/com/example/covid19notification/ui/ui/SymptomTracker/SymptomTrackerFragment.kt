package com.example.covid19notification.ui.ui.SymptomTracker

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.covid19notification.ui.R

class SymptomTrackerFragment : Fragment() {

    companion object {
        fun newInstance() = SymptomTrackerFragment()
    }

    private lateinit var viewModel: SymptomTrackerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.symptom_tracker_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SymptomTrackerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}