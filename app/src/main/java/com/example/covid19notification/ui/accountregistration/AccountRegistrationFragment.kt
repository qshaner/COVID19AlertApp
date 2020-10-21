package com.example.covid19notification.ui.accountregistration

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.covid19notification.ui.R

class AccountRegistrationFragment : Fragment() {

    companion object {
        fun newInstance() = AccountRegistrationFragment()
    }

    private lateinit var viewModel: AccountRegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.account_registration_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AccountRegistrationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}