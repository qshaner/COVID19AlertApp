package com.example.covid19notification.ui.AccountDetails

import androidx.fragment.app.Fragment
import com.example.covid19notification.ui.SingleFragmentActivity

class AccountDetails : SingleFragmentActivity() {
    override fun createFragment(): Fragment {
        return AccountDetailsFragment()
    }

}