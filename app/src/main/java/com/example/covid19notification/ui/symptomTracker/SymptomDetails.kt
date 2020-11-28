package com.example.covid19notification.ui.symptomTracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.covid19notification.R
import java.util.ArrayList

class SymptomDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptom_details)

        getIncomingIntent();
    }

    private fun getIncomingIntent(){
        if(intent.hasExtra("date") && intent.hasExtra("symptoms")){
            var date = intent.getStringExtra("date")
            var symptomEntries: ArrayList<String?> = intent.getStringArrayListExtra("symptoms") as ArrayList<String?>

            setEntry(date, symptomEntries)
        }
    }

    private fun setEntry(entryDate:String?, entrySymptoms: ArrayList<String?>){
        var date: TextView = findViewById(R.id.symptomDate)
        date.text = entryDate;
        var symptoms: EditText = findViewById(R.id.etSymptomList)
        symptoms.setText(entrySymptoms.toString())
    }


}