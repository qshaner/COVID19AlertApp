package com.example.covid19notification.ui.accountDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.covid19notification.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class accountDetailsFragment : Fragment(), View.OnClickListener {
    companion object {
        fun newInstance() = accountDetailsFragment()
    }
    private lateinit var mEtUsername: EditText
    private lateinit var mEtEmail: EditText
    private lateinit var mEtAddress: EditText
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

        val submitAccountDetails: Button = v.findViewById(R.id.ButtonAccountDataUpdate)
        submitAccountDetails.setOnClickListener(this);
        val btnDeleteAccount: Button = v.findViewById(R.id.accountDetDelete)
        btnDeleteAccount.setOnClickListener(this)
        getAndSetAccountDetails();

        return v;
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.accountDetDelete -> deleteAccount();
            R.id.ButtonAccountDataUpdate -> updateAccountDetails();
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
            mEtUsername.setText(username);
        }
    }

    private fun getEmailFromDatabase() {
        var email = "";
        db.collection("users").document(userID).get().addOnSuccessListener { result ->
            email = result.data!!["email"].toString();
            Log.d(
                "dbAccess",
                "/n CurrentUser called. ->" + email + "<- email should be printed"
            )
            mEtEmail.setText(email);
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
            mEtAddress.setText(address);
        }
     }

    private fun updateAccountDetails(){
        //TODO: Test this
        var addressData = mEtAddress.text.toString();
        var emailData = mEtEmail.text.toString();
        var usernameData = mEtUsername.text.toString();

        if(emailData == ""){
            //TODO: Make the toast
            //Make a toast that says 'You can't do this!'
        }
        else {
            auth.currentUser!!.updateEmail((emailData))
                //TODO: Add success listener
                //check if this succeeded or nah
        }
        //update address and username with users

        db.collection("users").document(userID).get().addOnSuccessListener { result ->
            //we don't want to set the email/username to blank if we can avoid it
            if(addressData == "N/A"){
                db.collection("users").document(userID).get().addOnSuccessListener { result ->
                    addressData = result.data!!["address"].toString()};
            }
            if(usernameData == ""){
                db.collection("users").document(userID).get().addOnSuccessListener { result ->
                    usernameData = result.data!!["username"].toString()};
            }
            if(emailData == ""){
                db.collection("users").document(userID).get().addOnSuccessListener { result ->
                    emailData = result.data!!["email"].toString()};
            }
                result.data!!["address"] = addressData;
                result.data!!["username"] = usernameData;
                result.data!!["email"] = emailData
            }
        }

    private fun deleteAccount(){
        auth.currentUser!!.delete();
        db.collection("users").document(userID).delete()

        Log.d(
            "dbDeleteAccount",
            "UserAccount and Auth record: "+userID + " has been deleted"
        )
    }
}