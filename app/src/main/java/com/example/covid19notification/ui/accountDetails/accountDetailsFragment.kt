package com.example.covid19notification.ui.accountDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.AppCompatTextView
import com.example.covid19notification.R


class accountDetailsFragment : Fragment(), View.OnClickListener {
    companion object {
        fun newInstance() = accountDetailsFragment()
    }
    private lateinit var mEtUsername: AppCompatTextView
    private lateinit var mEtEmail: AppCompatTextView
    private lateinit var mEtAddress: AppCompatTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_account_details, container, false)
        mEtUsername = v.findViewById(R.id.accountDetailNameData)
        mEtEmail = v.findViewById(R.id.AccountDetailEmailData)
        mEtAddress = v.findViewById(R.id.AccountDetailEmailData)

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
        mEtUsername.text =(username.toString());
        mEtEmail.text =(email.toString());
        mEtAddress.text = address.toString()
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