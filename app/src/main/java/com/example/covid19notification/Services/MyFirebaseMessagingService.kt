package com.example.covid19notification.Services

import android.util.Log
import com.example.covid19notification.Database.Tokens
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    private val TAG = "FirebaseMessage"
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("NEW_TOKEN", "Token ${token}")
        Tokens.add(token)
            .addOnSuccessListener {
                Log.d("TOKEN_ADD:Success", "Successfully added new token")
            }
            .addOnFailureListener {
                Log.d("TOKEN_ADD:Failed", "Failed to add token to databse")
            }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "From: ${remoteMessage.from}")

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")


        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
        }

    }
}