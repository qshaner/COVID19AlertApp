package com.example.covid19notification.ui.AccountDetails

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import com.example.covid19notification.Database.Users
import com.example.covid19notification.Helpers.Tags
import com.example.covid19notification.Model.User
import com.example.covid19notification.R
import com.example.covid19notification.ui.login.Login
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class AccountDetailsFragment : Fragment(), View.OnClickListener {
    companion object {
        fun newInstance() = AccountDetailsFragment()
    }
    private lateinit var mEtUsername: EditText
    private lateinit var mEtEmail: EditText
    private lateinit var mEtAddress: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var userID: String
    private lateinit var user: User
    private lateinit var mDarkModeSwitch: Switch
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_account_details, container, false)
        mEtUsername = v.findViewById(R.id.accountDetailNameData)
        mEtEmail = v.findViewById(R.id.AccountDetailEmailData)
        mEtAddress = v.findViewById(R.id.AaccountDetailAddressData)
        mDarkModeSwitch = v.findViewById(R.id.darkModeSwitch)
        user = requireActivity().intent.getSerializableExtra("user") as User
        sharedPreferences = requireActivity().getSharedPreferences("COM.COVID19NOTIFICATION.SHARED_PREFS",
            Context.MODE_PRIVATE)
        val submitAccountDetails: Button = v.findViewById(R.id.ButtonAccountDataUpdate)
        submitAccountDetails.setOnClickListener(this)
        val btnDeleteAccount: Button = v.findViewById(R.id.accountDetDelete)
        btnDeleteAccount.setOnClickListener(this)
        setAccountDetails()
        mDarkModeSwitch.setOnCheckedChangeListener{_, isChecked ->
            switchTheme(isChecked)
        }
        mDarkModeSwitch.isChecked = sharedPreferences.getBoolean("DARK_MODE_PREFERRED", false)
        Log.d("CurrentUser", "User coming in is $user")
        return v
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.accountDetDelete -> deleteAccount()
            R.id.ButtonAccountDataUpdate -> updateAccountDetails()
        }
    }

    private fun setAccountDetails() {
        mEtUsername.setText(user.getUsername())
        mEtAddress.setText(user.getAddress())
        mEtEmail.setText(user.getEmail())
    }


    private fun updateAccountDetails() {
        val addressData = mEtAddress.text.toString()
        val emailData = mEtEmail.text.toString()
        val usernameData = mEtUsername.text.toString()

        if (emailData != "" && emailData != user.getEmail() ) {
            user.setEmail(emailData)
            auth.currentUser!!.updateEmail(emailData)
                .addOnSuccessListener {
                    Users.changeEmail(user, emailData)
                        .addOnSuccessListener {
                            user.setEmail(emailData)
                            setAccountDetails()
                            Log.d(Tags.CHANGE_EMAIL_SUCCESS, "Successfully changed users email to ${user.getEmail()}")
                        }
                        .addOnFailureListener {
                            Log.d(Tags.CHANGE_EMAIL_FAILED, it.message.toString())
                            Toast.makeText(requireContext().applicationContext, "Error occurred trying to change the email", Toast.LENGTH_SHORT).show()
                        }

                    }
                .addOnFailureListener {
                    Log.d(Tags.CHANGE_EMAIL_FAILED, it.message.toString())
                    Toast.makeText(
                        requireContext().applicationContext,
                        "Error occurred trying to change the email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            if (usernameData != "" && usernameData != user.getUsername()) {
                user.setUsername(usernameData)
                Users.changeName(user, usernameData)
                    .addOnSuccessListener {
                        setAccountDetails()
                    }
                    .addOnFailureListener {

                    }

            }
        if (addressData != "" && addressData != user.getAddress()) {
            user.setAddress(addressData)
            Users.changeAddress(user, addressData)
                .addOnSuccessListener {
                    setAccountDetails()
                }
                .addOnFailureListener {

                }
        }
        val intent = Intent()
        intent.putExtra("user", user)
        requireActivity().setResult(Activity.RESULT_OK, intent)
    }

    private fun switchTheme(isChecked: Boolean) {
        if (isChecked) { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        sharedPreferences.edit().putBoolean("DARK_MODE_PREFERRED", isChecked).apply()

    }
    private fun deleteAccount() {
        val activity = requireActivity()
        auth.currentUser!!.delete()
            .addOnSuccessListener {
                Users.delete(user)
                    .addOnSuccessListener {
                        Log.d(Tags.USERS_DELETE_SUCCESS, "UserAccount and Auth record: $userID has been deleted")
                        Toast.makeText(
                            activity.applicationContext,
                            "Successfully Deleted Account",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(activity.applicationContext, Login::class.java))
                        activity.finish()
                    }
                    .addOnFailureListener {
                        Log.d(Tags.USERS_DELETE_FAILED, "Error occurred: ${it.message.toString()}}")
                        Toast.makeText(
                            activity.applicationContext,
                            "Error occurred while trying to delete account",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
            .addOnFailureListener {
                Log.d(Tags.USERS_DELETE_FAILED, "Error occurred: ${it.message.toString()}}")
                Toast.makeText(
                    activity.applicationContext,
                    "Error occurred while trying to delete account",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}