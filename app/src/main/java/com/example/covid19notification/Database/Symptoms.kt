package com.example.covid19notification.Database

import com.example.covid19notification.Model.Symptom
import com.example.covid19notification.Model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore


class Symptoms {
    companion object {
        private val COLLECTION_NAME = "symptoms"
        private val db = FirebaseFirestore.getInstance().collection(COLLECTION_NAME)

        fun add(user: User,id: String, symptoms: HashMap<String, Any>): Task<Void> {
            return db.document(user.id).update(symptoms)
        }

        fun update(user: User, symptoms: HashMap<String, Any>): Task<Void> {
            return db.document(user.id).update(symptoms)
        }

        fun delete(user: User, id: String): Task<Void> {
            return db.document("${user.id}").update("$id", FieldValue.delete())
        }

        fun getSymptoms(user: User): Task<DocumentSnapshot> {
            return db.document(user.id).get()
        }
    }
}