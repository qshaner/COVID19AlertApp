package com.example.covid19notification.Database

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class Database {
    companion object {

        private val db = FirebaseFirestore.getInstance()

        fun addToDatabase(collection: String, documentId: String, data: Any): Task<Void> {
            return db.collection(collection).document(documentId).set(data)
        }
        fun deleteFromDatabase(collection: String, documentId: String): Task<Void> {
            return db.collection(collection).document(documentId).delete()
        }
        fun updateDatabase(collection: String, documentId: String, key: String, value: Any): Task<Void> {
            return db.collection(collection).document(documentId).update(key, value)
        }
        fun getDataFromDatabase(collection: String, documentId: String): Task<DocumentSnapshot> {
            return db.collection(collection).document(documentId).get()
        }
    }

}
