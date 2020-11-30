package com.example.covid19notification.ui.symptomTracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19notification.Helpers.ResultCodes
import com.example.covid19notification.Model.Symptom
import com.example.covid19notification.Model.User
import com.example.covid19notification.R

class SymptomTracker : AppCompatActivity(), View.OnClickListener {
    private var TAG = "SymptomTrackerActivity"
    private var mSymptomEntries: ArrayList<Symptom> = ArrayList()
    private var ids: ArrayList<String> = ArrayList()
    private lateinit var btnAddSymptomEntry: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var user: User
    private lateinit var symptoms: HashMap<String, Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptom_tracker)

        Log.d(TAG, "Symptom Tracker On Create")
        btnAddSymptomEntry = findViewById(R.id.addSymptomEntry)
        btnAddSymptomEntry.setOnClickListener(this)
        recyclerView = findViewById(R.id.recycler_view)
        user = intent.getSerializableExtra("user") as User
        symptoms = intent.getSerializableExtra("symptoms") as HashMap<String, Any>
        initSymptomEntries()
    }

    private fun initSymptomEntries(){
        Log.d(TAG, "initSymptomEntries: Preparing Symptom Entries")
        symptoms.forEach {(key, value) ->
            val innerMap = value as HashMap<String, String>
            val date = innerMap.get("date") as String
            val text = innerMap.get("text") as String
            val symptom = Symptom(date, text)
            mSymptomEntries.add(symptom)
            ids.add(key)
        }
        initRecyclerView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (data != null) {
                if (data.hasExtra("id")) {
                    val id = data!!.getSerializableExtra("id") as String
                    if (data.hasExtra("map") && resultCode == ResultCodes.UPDATE) {
                        val symptom = data!!.getSerializableExtra("map") as Map<String, Any>
                        symptoms.putAll(symptom)
                    } else if (resultCode == ResultCodes.DELETE) {
                        val symptom = symptoms.remove(id)
                        Log.d("REMOVING SYMPTOM", "Symptom id to remove: $id, $symptom")
                    }
                    mSymptomEntries = ArrayList()
                    ids = ArrayList()
                    initSymptomEntries()
                }
            }
        }
    }


    private fun initRecyclerView(){
        Log.d(TAG, "initRecyclerView start")
        val adapter = SymptomAdapter(this, mSymptomEntries, ids, user)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun launchAddSymptomEntry() {
        val intent = Intent(this.applicationContext, SymptomDetails::class.java)
        intent.putExtra("user", user)
        startActivityForResult(intent, 1)

    }
    override fun onClick(v: View?) {
        val activity = this
        if (v != null) {
            when (v.id) {
                R.id.addSymptomEntry -> launchAddSymptomEntry()
            }
        }
    }

}