package com.example.covid19notification.ui.accountregistration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AccountRegistration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_registration_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AccountRegistrationFragment.newInstance())
                .commitNow()
        }
    }
}