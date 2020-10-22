package com.example.covid19notification.ui.accountregistration

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
import com.example.covid19notification.Model.User
import com.example.covid19notification.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import com.example.covid19notification.ui.Contact.contactActivtiy
import com.example.covid19notification.ui.accountDetails.accountDetails
import com.example.covid19notification.ui.login.Login
import com.example.covid19notification.ui.ui.SymptomTracker.SymptomTracker

class AccountRegistrationFragment  : Fragment(), View.OnClickListener {
    private lateinit var mEtUsername: EditText
    private lateinit var mEtPassword: EditText
    private lateinit var mEtEmail: EditText
    private lateinit var mEtAddress: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

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
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        val btnConfirm: Button = v.findViewById(R.id.buttonAccount)
        btnConfirm.setOnClickListener(this)

        val btnLogin: Button = v.findViewById(R.id.buttonLogin)
        btnLogin.setOnClickListener(this)

        return v
    }

    override fun onClick(v: View) {
        val activity = requireActivity()
        when (v.id) {
            R.id.buttonAccount -> createAccount();
            R.id.buttonLogin -> {startActivity(Intent(activity.applicationContext, Login::class.java))
            activity.finish()
            }
        }
    }

    private fun createAccount() {
        val username = mEtUsername.text.toString()
        val password = mEtPassword.text.toString()
        val email = mEtEmail.text.toString()
        val address = mEtAddress.text.toString()
        val activity = requireActivity()
        val tag = "AccountRegistration"

    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{task ->
        if (task.isSuccessful) {
            val id = auth.currentUser!!.uid
            val user = User(id, email, username, address)
            val document = db.collection("users").document(id)
            document.set(user).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(activity.applicationContext, "Successfully created account", Toast.LENGTH_SHORT).show()
                    Log.d("ACCOUNT_CREATION", "Account successfully created and added to db")
                    startActivity(Intent(activity.applicationContext, MainActivity::class.java))
                } else {
                    Log.e("addUser:failure", it.exception.toString())
                    Toast.makeText(activity.applicationContext, "Could not add user to database", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Log.e("createUser:failure", task.exception.toString());
            Toast.makeText(activity.applicationContext, "Registration Failed", Toast.LENGTH_SHORT).show()
        }
    }

    }

}