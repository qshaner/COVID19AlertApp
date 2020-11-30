package com.example.covid19notification.ui.symptomTracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.covid19notification.Database.Symptoms
import com.example.covid19notification.Helpers.ResultCodes
import com.example.covid19notification.Model.Symptom
import com.example.covid19notification.Model.User
import com.example.covid19notification.R
import kotlinx.android.synthetic.main.activity_symptom_details.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class SymptomDetails : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnSubmitSymptoms: Button
    private lateinit var btnDeleteEntry: Button
    private lateinit var user: User
    private var symptomId: String? = null
    private lateinit var mDateText: EditText
    private lateinit var mSymptomText: EditText
    private lateinit var mSymptoms: HashMap<String, Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptom_details)

        btnSubmitSymptoms = findViewById(R.id.btnSubmitSymptom)
        btnSubmitSymptoms.setOnClickListener(this)
        btnDeleteEntry = findViewById(R.id.btnDeleteSymptom)
        btnDeleteEntry.setOnClickListener(this)
        mDateText = findViewById(R.id.symptomDate)
        mSymptomText = findViewById(R.id.etSymptomList)
        mDateText.setText("")
        mDateText.setHint("Enter date: (MM-DD-YYYY)")

        getIncomingIntent();
    }

    override fun onClick(v:View){
        when(v.id){
            R.id.btnDeleteSymptom -> deleteEntry()
            R.id.btnSubmitSymptom -> submitEntry()
        }
    }

    fun deleteEntry(){
        Symptoms.delete(user, symptomId.toString())
            .addOnSuccessListener {
                Log.d("SYMPTOM_TRACKER_DELETE","Succesfully deleted symptom")
                val intent = Intent()
                intent.putExtra("id", symptomId)
                setResult(ResultCodes.DELETE, intent)
                finish()
            }
            .addOnFailureListener {
                Log.d("SYMPTOM_TRACKER_DELETE","Failed to deleted symptom")
            }
    }

    fun submitEntry(){

        var text = etSymptomList.text.toString()
        var dateEntry = symptomDate.text.toString();
        val symptom = Symptom(dateEntry, text)
        val map = hashMapOf<String, Any>(symptomId.toString() to mapOf(
            "date" to dateEntry,
            "text" to text
        ))
        Symptoms.update(user, map)
            .addOnSuccessListener {
                Toast.makeText(this.applicationContext, "Successfully updated entries", Toast.LENGTH_SHORT).show()
                Log.d("ADD_SYMPTOM", "Successfully added new symptom")
                val intent = Intent()
                intent.putExtra("map", map)
                intent.putExtra("id", symptomId)
                setResult(ResultCodes.UPDATE, intent)

            }
            .addOnFailureListener {
                Log.d("ADD_SYMPTOM", "${it.message.toString()}")
                Toast.makeText(this.applicationContext, "An error occurred", Toast.LENGTH_SHORT).show()
            }
    }

    private fun getIncomingIntent(){
        if (intent.hasExtra("id")) {
            symptomId = intent.getStringExtra("id").toString()
        } else if (symptomId == null){
            symptomId = UUID.randomUUID().toString()
        }
        var date = ""
        var text = ""

        if (intent.hasExtra("date")) {
            date = intent.getStringExtra("date") as String
        }
        if (intent.hasExtra("text")) {
            text = intent.getStringExtra("text").toString()
        }
        if (intent.hasExtra("user")) {
            user = intent.getSerializableExtra("user") as User
        }

        if (intent.hasExtra("symptoms")) {
            mSymptoms = intent.getSerializableExtra("symptoms") as HashMap<String, Any>
        }

        setEntry(date, text)
    }

    private fun setEntry(date: String, text: String){
        mDateText.setText(date);
        mSymptomText.setText(text)
    }


}