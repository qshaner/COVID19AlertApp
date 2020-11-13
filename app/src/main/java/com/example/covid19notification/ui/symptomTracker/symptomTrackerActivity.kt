package com.example.covid19notification.ui.symptomTracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.covid19notification.Model.Symptom
import com.example.covid19notification.R
import com.example.covid19notification.ui.SingleFragmentActivity

class symptomTrackerActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment? {
        setContentView((R.layout.activity_symptom_tracker))
        return SymptomTracker()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptom_tracker)
    }
}