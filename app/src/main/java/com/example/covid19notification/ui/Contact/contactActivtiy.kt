package com.example.covid19notification.ui.Contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covid19notification.R
import com.example.covid19notification.ui.Contact.ContactActivtiyFragment

class contactActivtiy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_activtiy_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ContactActivtiyFragment.newInstance())
                .commitNow()
        }
    }
}