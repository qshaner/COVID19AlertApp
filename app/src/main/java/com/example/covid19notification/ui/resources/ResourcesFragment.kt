package com.example.covid19notification.ui.resources

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.covid19notification.R

class ResourceFragment : Fragment() {

    private lateinit var resourceViewModel: ResourceViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        resourceViewModel =
                ViewModelProviders.of(this).get(ResourceViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_resources, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        resourceViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}