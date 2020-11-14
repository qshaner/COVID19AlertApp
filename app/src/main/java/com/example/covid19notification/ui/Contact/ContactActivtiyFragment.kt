package com.example.covid19notification.ui.Contact

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.covid19notification.R

class ContactActivtiyFragment : Fragment() {

    companion object {
        fun newInstance() =
            ContactActivtiyFragment()
    }

    private lateinit var viewModel: contactActivityViewModel



    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

       val root = inflater.inflate(R.layout.contact_activity_fragment, container, false)
        val date: EditText = root.findViewById(R.id.editTextDatePositive)

       val button : Button = root.findViewById(R.id.buttonContactTracing)



        button.setOnClickListener{
            val dateVal = date.text.toString()
            Log.i("On Create View", "Button Clicked. ")
            val SMSIntent = Intent(Intent.ACTION_VIEW).apply {
                setData(Uri.parse("sms:"));putExtra(
                "sms_body",
                "Hello. Unfortunately, I have tested positive for Covid-19 on $dateVal. Because I was around you within this time frame, it might be smart to quarentine until you can take a Covid-19 test."
            )
            }

            Log.i("Send Message Fun", "Beginning Activity.")
            startActivity(SMSIntent)
        }


        return root
    }





    private fun sendMessage(){

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(contactActivityViewModel::class.java)
        // TODO: Use the ViewModel
    }

}