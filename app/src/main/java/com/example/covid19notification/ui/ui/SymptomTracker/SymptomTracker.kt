package com.example.covid19notification.ui.ui.SymptomTracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}