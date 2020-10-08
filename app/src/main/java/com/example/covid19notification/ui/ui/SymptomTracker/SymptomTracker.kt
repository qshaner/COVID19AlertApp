package com.example.covid19notification.ui.ui.SymptomTracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covid19notification.R
import com.example.covid19notification.ui.ui.SymptomTracker.SymptomTrackerFragment

class SymptomTracker : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.symptom_tracker_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SymptomTrackerFragment.newInstance())
                .commitNow()
        }
    }

    //If you are logging your symptoms, you probably do not want to restart if your phone gets turned.
}

