package com.example.covid19notification.Model

import java.io.Serializable

data class Symptom(val date: String, val text: String): Serializable {
    override fun toString() = "Date: $date, symptoms: $text"
}