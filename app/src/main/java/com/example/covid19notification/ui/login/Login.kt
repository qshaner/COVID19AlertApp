package com.example.covid19notification.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.covid19notification.R
import com.example.covid19notification.ui.SingleFragmentActivity

class Login : SingleFragmentActivity() {
    override fun createFragment(): Fragment {
            return LoginFragment()
        }
}