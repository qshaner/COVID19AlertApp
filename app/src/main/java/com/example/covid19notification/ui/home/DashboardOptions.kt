package com.example.covid19notification.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.covid19notification.R

class DashboardOptions : AppCompatActivity() {

    val tag = "DashboardOptions"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_options)
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume called.")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause() called.")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop() called.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy() called. ")
    }



}