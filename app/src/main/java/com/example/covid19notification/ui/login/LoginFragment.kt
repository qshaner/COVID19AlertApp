package com.example.covid19notification.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.covid19notification.R
import com.example.covid19notification.ui.accountregistration.AccountRegistration


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



        val btnLogin: Button = v.findViewById(R.id.login)
        btnLogin.setOnClickListener(this)

        val btnSignup: Button = v.findViewById(R.id.signup)
        btnSignup.setOnClickListener(this)

        return v;
    }

    override fun onClick(v: View) {
        val activity = requireActivity()
        when (v.id) {
            R.id.login -> login();
            R.id.signup -> startActivity(
                Intent(
                    activity.applicationContext,
                    AccountRegistration::class.java
                )
            )
        }
    }

        private fun login(){
            val username = mEtUsername.text.toString()
            val password = mEtPassword.text.toString()
            //check the input against hte database
            //if yes, move on
            //if no, stay here

            //TODO: Set up database connection to check for user and password

        }
    }
