package com.example.covid19notification.Database

import com.example.covid19notification.Model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class Users {
    companion object {
        private val COLLECTION_NAME = "users"
        private val db = FirebaseFirestore.getInstance().collection(COLLECTION_NAME)

        fun add(user: User): Task<Void> {
            return db.document(user.id).set(user)
        }
        fun delete(id: String): Task<Void> {
           return db.document(id).delete()
        }

        fun update(id: String, key: String, value: Any): Task<Void> {
            return db.document(id).update(key, value)
        }
        fun changeAddress(id: String, address: String): Task<Void> {
            return update(id, "address", address)
        }
        fun changeName(id: String, name: String): Task<Void> {
            return update(id, "name", name)
        }

        fun changeEmail(id: String, email: String): Task<Void> {
            return update(id, "email", email)
        }
        fun get(id: String): Task<DocumentSnapshot> {
            return db.document(id).get()
        }
    }
}