package com.example.covid19notification.ui.home

import android.app.Activity.RESULT_OK
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
import com.example.covid19notification.Model.User
import com.example.covid19notification.R
import com.example.covid19notification.ui.Contact.contactActivtiy
import com.example.covid19notification.ui.AccountDetails.AccountDetails
import com.example.covid19notification.ui.symptomTracker.SymptomTracker

class HomeFragment : Fragment(), View.OnClickListener, SensorEventListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var mSensorManager: SensorManager
    private var mSensor: Sensor? = null
    private lateinit var user: User
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home, container)
        sharedPreferences = requireActivity().getSharedPreferences("COM.COVID19NOTIFICATION.SHARED_PREFS", Context.MODE_PRIVATE)
        mSensorManager = requireActivity().getSystemService(SENSOR_SERVICE) as SensorManager
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
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
        val btnNotify: Button = root.findViewById(R.id.button_notify)
        btnNotify.setOnClickListener(this)
        val btnSymptomTracker: Button = root.findViewById(R.id.button_symptomTracker)
        btnSymptomTracker.setOnClickListener(this)
        val btnAccount: Button = root.findViewById(R.id.button_account_details)
        btnAccount.setOnClickListener(this)
        user = requireActivity().intent.getSerializableExtra("user") as User
        return root
    }

    private fun showAlert() {
     AlertDialog.Builder(requireContext())
         .setTitle("Hey, you're moving kind of fast.")
         .setMessage("This is just a friendly reminder to bring your mask if you are going out in public.")
         .setNeutralButton("OK",{_, _ ->  })
         .show()
    }

    override fun onClick(v:View){
        val activity = requireActivity()
        when(v.id){

            R.id.button_symptomTracker -> launchSymptomTracker()

            R.id.button_notify -> launchContactActivity()

            R.id.button_account_details -> launchAccountDetails()
            //permission button here as well
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            user = data!!.getSerializableExtra("user") as User
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

    private fun launchSymptomTracker() {
        val intent = Intent(requireActivity().applicationContext, SymptomTracker::class.java)
        intent.putExtra("user", user)
        startActivityForResult(intent, 1)
    }

    private fun launchContactActivity() {
        startActivity(Intent(requireActivity().applicationContext, contactActivtiy::class.java))
    }

    private fun launchAccountDetails() {
        val intent = Intent(requireActivity().applicationContext, AccountDetails::class.java)
        intent.putExtra("user", user)
        startActivityForResult(intent, 1)
    }


//TODO: Needs to have the Navbar as well

}