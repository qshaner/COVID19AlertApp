package com.example.covid19notification.ui.symptomTracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19notification.Database.Symptoms
import com.example.covid19notification.Model.Symptom
import com.example.covid19notification.Model.User
import com.example.covid19notification.R

private lateinit var btnAddSymptomEntry: ImageButton

class SymptomTracker : AppCompatActivity(), View.OnClickListener {
    private var TAG = "SymptomTrackerActivity"

    private var mSymptomEntries: ArrayList<Symptom> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptom_tracker)

        Log.d(TAG, "Symptom Tracker On Create")
        btnAddSymptomEntry = findViewById(R.id.addSymptomEntry)
        btnAddSymptomEntry.setOnClickListener(this)
        initSymptomEntries()
    }

    private fun initSymptomEntries(){
        Log.d(TAG, "initSymptomEntries: Preparing Symptom Entries")


       var userID = (intent.getSerializableExtra("user") as User).id;
        Symptoms.getAllEntries(userID).addOnSuccessListener { result ->
            for (document in result){
                mSymptomEntries.add(Symptom(document["date"].toString(), document["symptoms"] as ArrayList<String>))
                Log.d("SympTracker", "document data: $document")
            }
            Log.d("SympTracker2: ", "What is result: ${result.documents}")
        }


    /*    var symptoms: ArrayList<String> = arrayListOf<String>("symptom 1", "symptom 2")
        mSymptomEntries.add(Symptom("Date 1", symptoms ))
        symptoms = arrayListOf<String>("symptom 1, symptom 3, symptom 4, symptom 6, symptom 5, symptom 7")
        mSymptomEntries.add(Symptom("Date 2", symptoms ))
        symptoms = arrayListOf<String>("symptom 3, symptom 5, symptom 6")
        mSymptomEntries.add(Symptom("Date 3", symptoms ))
        symptoms = arrayListOf<String>("Symptoms for Entry 4")
        mSymptomEntries.add(Symptom("Date 4", symptoms ))
        symptoms = arrayListOf<String>("Symptoms for Entry 5")
        mSymptomEntries.add(Symptom("Date 5", symptoms ))
        symptoms = arrayListOf<String>("Symptoms for Entry 6")
        mSymptomEntries.add(Symptom("Date 6", symptoms ))
        symptoms = arrayListOf<String>("Symptoms for Entry 7")
        mSymptomEntries.add(Symptom("Date 7", symptoms ))
        symptoms = arrayListOf<String>("Symptoms for Entry 8")
        mSymptomEntries.add(Symptom("Date 8", symptoms ))
        symptoms = arrayListOf<String>("Symptoms for Entry 9")
        mSymptomEntries.add(Symptom("Date 9", symptoms ))
        symptoms = arrayListOf<String>("Symptoms for Entry 10")
        mSymptomEntries.add(Symptom("Date 10", symptoms ))
        symptoms = arrayListOf<String>("Symptoms for Entry 11")
        mSymptomEntries.add(Symptom("Date 11", symptoms ))
*/
        initRecyclerView()
    }


    private fun initRecyclerView(){
        Log.d(TAG, "initRecyclerView start")
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = SymptomAdapter(this, mSymptomEntries)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }


    override fun onClick(v: View?) {
        val activity = this
        if (v != null) {
            when (v.id) {
                R.id.addSymptomEntry -> startActivity(
                    Intent(
                        activity.applicationContext,
                        SymptomDetails::class.java
                    )
                )
            }
        }
    }

}