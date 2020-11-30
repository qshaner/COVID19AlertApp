package com.example.covid19notification.Database

import com.example.covid19notification.Model.Symptom
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore


class Symptoms {
    companion object {
        private val auth = FirebaseAuth.getInstance()
        private val COLLECTION_NAME = "users"
        private val db_users = FirebaseFirestore.getInstance().collection("users")

        fun add(symptom: Symptom): Task<Void> {
            val userid = auth.currentUser!!.uid
          return FirebaseFirestore.getInstance().collection("users").document(userid).update("SymptomEntries.${symptom.date}", symptom.symptoms)

        }
        fun getSymptomsForDate(symptom: Symptom): Task<DocumentSnapshot> {
            val userid = auth.currentUser!!.uid
            return FirebaseFirestore.getInstance().collection("users").document("${userid}.SymptomEntries.${symptom.date}").get()
        }

        fun getAllSymptomEntries(): Task<DocumentSnapshot>{
            val userid = auth.currentUser!!.uid
            return FirebaseFirestore.getInstance().collection("users").document("${userid}.SymptomEntries").get()
        }

        fun delete(symptom: Symptom): Task<Void> {
            val userid = auth.currentUser!!.uid
            return FirebaseFirestore.getInstance().collection("users").document(userid).update("SymptomEntries.${symptom.date}", FieldValue.arrayRemove(symptom.symptoms))
        }
    }
}