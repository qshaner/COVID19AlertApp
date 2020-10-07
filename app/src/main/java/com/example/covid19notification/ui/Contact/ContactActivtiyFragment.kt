package com.example.covid19notification.ui.Contact

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.covid19notification.ui.R

class ContactActivtiyFragment : Fragment() {

    companion object {
        fun newInstance() =
            ContactActivtiyFragment()
    }

    private lateinit var viewModel: contactActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.contact_activity_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(contactActivityViewModel::class.java)
        // TODO: Use the ViewModel
    }

}