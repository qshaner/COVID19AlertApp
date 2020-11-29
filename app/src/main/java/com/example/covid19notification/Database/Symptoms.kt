package com.example.covid19notification.Database

import android.util.Log
import com.example.covid19notification.Model.Symptom
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*

class Symptoms {
    companion object {
        private val COLLECTION_NAME = "users"
        private val db_users = FirebaseFirestore.getInstance().collection(COLLECTION_NAME)

        //Add to user id the symptoms entry
        //Use the date as the id
        fun add(userid:String, symptom: Symptom): Task<Void> {
            return db_users.document(userid).collection("symptoms").document(symptom.date.toString()).update("symptoms", FieldValue.arrayUnion(symptom))
        }
        fun get(userid: String, date: String): Task<DocumentSnapshot> {
            return db_users.document(userid).collection("symptoms").document(date.toString()).get()
        }

        fun update(userid:String, symptom: Symptom): Task<Void> {
            return db_users.document(userid).collection("symptoms").document(symptom.date.toString()).update("symptoms", FieldValue.arrayUnion(symptom))
        }
        fun delete(userid:String, symptom: Symptom): Task<Void> {
            return db_users.document(userid).collection("symptoms").document(symptom.date.toString()).update("symptoms", FieldValue.arrayRemove(symptom))
        }

       fun getAllEntries(userid:String): Task<QuerySnapshot> {
           return db_users.document(userid).collection("symptoms").get()
       }


    }
}