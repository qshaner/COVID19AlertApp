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
        fun delete(user: User): Task<Void> {
           return db.document(user.id).delete()
        }

        fun update(user: User, key: String, value: Any): Task<Void> {
            return db.document(user.id).update(key, value)
        }
        fun changeAddress(user: User, address: String): Task<Void> {
            return update(user, "address", address)
        }
        fun changeName(user: User, name: String): Task<Void> {
            return update(user,"username", name)
        }

        fun changeEmail(user: User, email: String): Task<Void> {
            return update(user, "email", email)
        }
        fun get(id: String): Task<DocumentSnapshot> {
            return db.document(id).get()
        }
    }
}