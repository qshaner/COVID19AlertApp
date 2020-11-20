package com.example.covid19notification.ui.home

import android.app.AlertDialog
import android.content.*
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.covid19notification.Helpers.Tags
import com.example.covid19notification.R
import com.example.covid19notification.ui.Contact.contactActivtiy
import com.example.covid19notification.ui.accountDetails.accountDetails
import com.example.covid19notification.ui.symptomTracker.symptom_tracker

class HomeFragment : Fragment(), View.OnClickListener, SensorEventListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var mSensorManager: SensorManager
    private var mSensor: Sensor? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home, container)
        sharedPreferences = requireActivity().getSharedPreferences("COM.COVID19NOTIFICATION.SHARED_PREFS", Context.MODE_PRIVATE)
        mSensorManager = requireActivity().getSystemService(SENSOR_SERVICE) as SensorManager
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        if (mSensor == null) {
                Log.e(Tags.SENSOR_UNAVAILABLE, "linear accelerometer unavailable")
        }
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

    private fun showAlert() {
     AlertDialog.Builder(requireContext())
         .setTitle("Hey, you're moving kind of fast.")
         .setMessage("This is just a friendly reminder to bring your mask if you are going out in public.")
         .setNeutralButton("OK",{dialogInterface, i ->  })
         .show()
    }

    override fun onClick(v:View){
        val activity = requireActivity()
        when(v.id){

            R.id.button_symptomTracker -> startActivity(Intent(activity.applicationContext, symptom_tracker::class.java))

            R.id.button_notify -> {startActivity(Intent(activity.applicationContext, contactActivtiy::class.java))
                Log.i("Home Click", "Contact activity selected.")
                }

            R.id.button_account_details -> startActivity(Intent(activity.applicationContext, accountDetails::class.java))
            //permission button here as well
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return
        if (event.sensor.type == Sensor.TYPE_LINEAR_ACCELERATION) {
            val timeElapsed = sharedPreferences.getLong("LAST_NOTIFICATION_TIMESTAMP", 0)
            val ONE_HOUR = 120000 // in milliseconds
            val accelerationX = event.values[0]
            if (accelerationX > 0.7 && System.currentTimeMillis() - timeElapsed > ONE_HOUR) {
                sharedPreferences.edit()
                    .putLong("LAST_NOTIFICATION_TIMESTAMP", System.currentTimeMillis())
                showAlert()
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
//TODO: Needs to have the Navbar as well

}