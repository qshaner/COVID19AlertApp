package com.example.covid19notification.ui.accountDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.AppCompatTextView
import com.example.covid19notification.Database.Database
import com.example.covid19notification.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


class accountDetailsFragment : Fragment(), View.OnClickListener {
    companion object {
        fun newInstance() = accountDetailsFragment()
    }
    private lateinit var mEtUsername: AppCompatTextView
    private lateinit var mEtEmail: AppCompatTextView
    private lateinit var mEtAddress: AppCompatTextView
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var userID: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_account_details, container, false)
        mEtUsername = v.findViewById(R.id.accountDetailNameData)
        mEtEmail = v.findViewById(R.id.AccountDetailEmailData)
        mEtAddress = v.findViewById(R.id.AaccountDetailAddressData)
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        userID = auth.currentUser!!.uid.toString();


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
        getEmailFromDatabase()
        getCurrentUser()
        getAddressFromDatabase()
        val activity = requireActivity()
    }

    private fun getCurrentUser() {
        var username = "";
        db.collection("users").document(userID).get().addOnSuccessListener { result ->
            username = result.data!!["username"].toString();
                Log.d(
                    "dbAccess",
                    "/n CurrentUser called. ->" + username + "<- Username should be printed"
                )
            mEtUsername.text = username
        }
    }

    private fun getEmailFromDatabase() {
      //  return auth.currentUser!!.email;
        var email = "";
        db.collection("users").document(userID).get().addOnSuccessListener { result ->
            email = result.data!!["email"].toString();
            Log.d(
                "dbAccess",
                "/n CurrentUser called. ->" + email + "<- email should be printed"
            )
            mEtEmail.text = email;
        }
    }

    private fun getAddressFromDatabase(){
        var address = "N/A";
        db.collection("users").document(userID).get().addOnSuccessListener { result ->
            address = result.data!!["address"].toString();
            Log.d(
                "dbAccess",
                "/n CurrentUser called. -> " + address + " <- address should be printed"
            )
            if(address==""){
                address = "N/A"
            }
            mEtAddress.text = address;
        }
     }

    //TODO: add in an update button and onClick, update the value for the current user in the DB
    private fun updateAddress(){}

    //TODO: Add in update button and textbox thing
    private fun updateEmail(){}

    private fun deleteAccount(){
        //TODO: Hook in database
    }
}