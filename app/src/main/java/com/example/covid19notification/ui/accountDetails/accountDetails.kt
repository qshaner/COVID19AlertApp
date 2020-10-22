package com.example.covid19notification.ui.accountDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.covid19notification.R
import com.example.covid19notification.ui.SingleFragmentActivity
import com.example.covid19notification.ui.ui.SymptomTracker.SymptomTrackerFragment

class accountDetails : SingleFragmentActivity() {
    override fun createFragment(): Fragment {
    return accountDetailsFragment()
    }

}