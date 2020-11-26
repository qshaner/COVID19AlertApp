package com.example.covid19notification.Model

import java.io.Serializable

class User(val id: String, private var email: String, private var username: String, private var address: String): Serializable {

    fun setEmail(email: String) {
        this.email = email
    }

    fun setAddress(address: String) {
        this.address = address
    }

    fun setUsername(name: String) {
        this.username = name
    }

    fun getEmail(): String = email

    fun getUsername(): String = username

    fun getAddress(): String = address

    override fun toString(): String  = "Name: $username, email: $email, id: $id, address: $address"
 }