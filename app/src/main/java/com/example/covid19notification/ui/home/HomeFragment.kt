package com.example.covid19notification.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.covid19notification.R
import com.example.covid19notification.ui.Contact.contactActivtiy
import com.example.covid19notification.ui.accountDetails.accountDetails
import com.example.covid19notification.ui.ui.SymptomTracker.SymptomTracker

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home, container)
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val btnPermissions: Button = root.findViewById(R.id.button_permission)
        btnPermissions.setOnClickListener(this)
        val btnNotify: Button = root.findViewById(R.id.button_notify)
        btnNotify.setOnClickListener(this)
        val btnSymptomTracker: Button = root.findViewById(R.id.button_symptomTracker)
        btnSymptomTracker.setOnClickListener(this)
        val btnAccount: Button = root.findViewById(R.id.button_account_details)
        btnAccount.setOnClickListener(this)
        return root
    }

    override fun onClick(v:View){
        val activity = requireActivity()
        when(v.id){
            R.id.button_notify -> startActivity(Intent(activity.applicationContext, contactActivtiy::class.java))
            R.id.button_symptomTracker -> startActivity(Intent(activity.applicationContext, SymptomTracker::class.java))
            R.id.button_account_details -> startActivity(Intent(activity.applicationContext, accountDetails::class.java))
            //permission button here as well
        }
    }
//TODO: Needs to have the Navbar as well

}