package com.example.covid19notification.ui.accountregistration

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.covid19notification.R
import com.example.covid19notification.ui.Contact.contactActivtiy
import com.example.covid19notification.ui.ui.SymptomTracker.SymptomTracker

class AccountRegistrationFragment  : Fragment(), View.OnClickListener {
    private lateinit var mEtUsername: EditText
    private lateinit var mEtPassword: EditText
    private lateinit var mEtEmail: EditText
    private lateinit var mEtAddress: EditText

    companion object {
        fun newInstance() = AccountRegistrationFragment()
    }

    //private lateinit var accountRegistrationViewModel: AccountRegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.account_registration_fragment, container, false)
        mEtUsername = v.findViewById(R.id.accountRegName)
        mEtPassword = v.findViewById(R.id.accountRegPass)
        mEtEmail = v.findViewById(R.id.accountRegEmail)
        mEtAddress = v.findViewById(R.id.accountRegLocation)

        val btnConfirm: Button = v.findViewById(R.id.buttonAccount)
        btnConfirm.setOnClickListener(this)

        return v
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonAccount -> createAccount();
        }
    }

    private fun createAccount() {
        val username = mEtUsername.text.toString()
        val password = mEtPassword.text.toString()
        val email = mEtEmail.text.toString()
        val address = mEtAddress.text.toString()
        val activity = requireActivity()

        //make sure that user, password, and email are non-empty
        //address can be empty

        //if empty, show error
    }

}