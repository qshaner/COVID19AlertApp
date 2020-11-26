package com.example.covid19notification.ui.accountregistration

import android.app.AlertDialog
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
import com.example.covid19notification.Database.Users
import com.example.covid19notification.Helpers.Tags
import com.example.covid19notification.MainActivity
import com.example.covid19notification.Model.User
import com.example.covid19notification.R
import com.google.firebase.auth.FirebaseAuth
import com.example.covid19notification.ui.login.Login
import com.google.firebase.iid.FirebaseInstanceId

class AccountRegistrationFragment  : Fragment(), View.OnClickListener {
    private lateinit var mEtUsername: EditText
    private lateinit var mEtPassword: EditText
    private lateinit var mEtEmail: EditText
    private lateinit var mEtAddress: EditText
    private lateinit var auth: FirebaseAuth

    companion object {
        fun newInstance() = AccountRegistrationFragment()
    }

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
        val btnConfirm: Button = v.findViewById(R.id.buttonAccount)
        btnConfirm.setOnClickListener(this)

        val btnLogin: Button = v.findViewById(R.id.buttonLogin)
        btnLogin.setOnClickListener(this)
        Log.d(Tags.FIREBASE_TOKEN, "Your token is ${FirebaseInstanceId.getInstance().token}")
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
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val id = auth.currentUser!!.uid
                val user = User(id, email, username, address)
                Users.add(user)
                    .addOnSuccessListener {
                        Toast.makeText(activity.applicationContext, "Successfully created account", Toast.LENGTH_SHORT).show()
                        Log.d(Tags.REGISTRATION_SUCCESS, "Account successfully created and added to db")
                        val intent = Intent(activity.applicationContext, MainActivity::class.java)
                        intent.putExtra("user", user)

                        startActivity(intent)
                    }
                    .addOnFailureListener {
                        Log.e(Tags.DB_ADD_USER_ERROR, it.message.toString())
                        Toast.makeText(activity.applicationContext, "Could not add user to database", Toast.LENGTH_SHORT).show()
                    }

            }
            .addOnFailureListener {
                Log.e(Tags.REGISTTRATION_FAILED, it.message.toString());
                Toast.makeText(activity.applicationContext, "Registration Failed", Toast.LENGTH_SHORT).show()

        }

    }

}