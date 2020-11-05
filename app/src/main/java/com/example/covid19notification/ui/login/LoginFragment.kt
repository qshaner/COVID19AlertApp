package com.example.covid19notification.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.covid19notification.Helpers.Tags
import com.example.covid19notification.MainActivity
import com.example.covid19notification.R
import com.example.covid19notification.ui.accountregistration.AccountRegistration
import com.example.covid19notification.ui.home.DashboardOptions
import com.google.firebase.auth.FirebaseAuth


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var mEtUsername: EditText
    private lateinit var mEtPassword: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_login, container, false)

        mEtUsername = v.findViewById(R.id.editTextTextEmailAddress2)
        mEtPassword = v.findViewById(R.id.editTextTextPassword)

        val btnLogin: Button = v.findViewById(R.id.login)
        btnLogin.setOnClickListener(this)

        val btnSignup: Button = v.findViewById(R.id.signup)
        btnSignup.setOnClickListener(this)

        return v;
    }

    override fun onClick(v: View) {
        val activity = requireActivity()
        when (v.id) {
            R.id.login -> checkLogin(v);
            R.id.signup -> startActivity(
                Intent(
                    activity.applicationContext,
                    AccountRegistration::class.java
                )
            )
        }
    }

        private fun checkLogin(v: View) {
            val activity = requireActivity()
            val username = mEtUsername.text.toString()
            val password = mEtPassword.text.toString()

            val auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(username, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.i(Tags.USER_LOGIN_SUCCESS, "User successfully logged in")
                    startActivity(Intent(activity.applicationContext, MainActivity::class.java))
                    activity.finish()
                } else {
                    Log.e(Tags.USER_LOGIN_FAILED, it.exception.toString())
                    Toast.makeText(activity.applicationContext, "Error occurred when trying to login", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
