package com.example.covid19notification.Database

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore

class Tokens {
    companion object {
        private val DB_NAME = "tokens"
        private val db = FirebaseFirestore.getInstance()
        private val collection = db.collection(DB_NAME)

        fun add(token: String): Task<Void> {
            return collection.document(token).set(token)
        }
    }
}