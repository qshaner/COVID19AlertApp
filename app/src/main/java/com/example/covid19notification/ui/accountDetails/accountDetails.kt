package com.example.covid19notification.ui.accountDetails

import androidx.fragment.app.Fragment
import com.example.covid19notification.ui.SingleFragmentActivity

class accountDetails : SingleFragmentActivity() {
    override fun createFragment(): Fragment {
    return accountDetailsFragment()
    }

}