package com.example.covid19notification.ui.resources

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.covid19notification.R
import com.example.covid19notification.ui.news.NewsActivity
import com.example.covid19notification.ui.news.NewsFragment
import com.example.covid19notification.ui.news.NewsViewModel
import com.example.covid19notification.ui.symptomTracker.SymptomTracker


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
        val CDCPage: WebView = root.findViewById(R.id.cdcWebView)
        CDCPage.settings.javaScriptEnabled = true

        CDCPage.loadUrl("https://www.cdc.gov/coronavirus/2019-ncov/index.html")

        val ST: Button = root.findViewById(R.id.resourcesToSymptomTracker)
        val CovidResourcesNearMe : Button = root.findViewById(R.id.resourcesToStatistics)

        ST.setOnClickListener{
            val intent = Intent(requireActivity().applicationContext, SymptomTracker::class.java)
            startActivityForResult(intent, 1)
        }

        CovidResourcesNearMe.setOnClickListener{
           CDCPage.loadUrl("https://www.google.com/search?q=covid+resources+near+me")
        }




        resourceViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}