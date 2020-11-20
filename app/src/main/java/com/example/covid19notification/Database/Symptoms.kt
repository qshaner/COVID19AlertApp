package com.example.covid19notification.Database

import com.example.covid19notification.Model.Symptom
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class Symptoms {
    companion object {
        private val COLLECTION_NAME = "symptoms"
        private val db = FirebaseFirestore.getInstance().collection(COLLECTION_NAME)

        fun add(id: String, symptom: Symptom): Task<Void> {
            return db.document(id).update("symptoms", FieldValue.arrayUnion(symptom))
        }
        fun get(id: String): Task<DocumentSnapshot> {
            return db.document(id).get()
        }

        fun update(id: String, symptom: Symptom): Task<Void> {
            return db.document(id).update("symptoms", FieldValue.arrayUnion(symptom))
        }
        fun delete(id: String, symptom: Symptom): Task<Void> {
            return db.document(id).update("symptoms", FieldValue.arrayRemove(symptom))
        }
    }
}