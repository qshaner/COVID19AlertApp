package com.example.covid19notification.ui.accountDetails

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.covid19notification.MainActivity
import com.example.covid19notification.R
import com.example.covid19notification.ui.ui.SymptomTracker.SymptomTrackerFragment


class accountDetailsFragment : Fragment(), View.OnClickListener {
    companion object {
        fun newInstance() = SymptomTrackerFragment()
    }
    private lateinit var mEtUsername: EditText
    private lateinit var mEtEmail: EditText
    private lateinit var mEtAddress: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_account_details, container, false)
        mEtUsername = v.findViewById(R.id.accountDetName)
        mEtEmail = v.findViewById(R.id.accountDetEmail)
        mEtAddress = v.findViewById(R.id.accountDetAddress)

        val btnDeleteAccount: Button = v.findViewById(R.id.accountDetDelete)
        btnDeleteAccount.setOnClickListener(this)
        getAndSetAccountDetails();
        return v;
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.accountDetDelete -> deleteAccount();
        }
    }

    private fun getAndSetAccountDetails() {
        val username = getCurrentUser()
        val email = getEmailFromDatabase()
        val address = getAddressFromDatabase()
        mEtUsername.setText(username.toString());
        mEtEmail.setText(email.toString());
        mEtAddress.setText(address.toString())
        val activity = requireActivity()
    }

    private fun getCurrentUser(){
        //TODO: Hook in database
    }

    private fun getEmailFromDatabase(){
        //TODO: Hook in database
        //TODO: Can make this editable with confirm button?
    }

    private fun getAddressFromDatabase(){
        //TODO: Hook in database
    }

    private fun deleteAccount(){
        //TODO: Hook in database
    }
}