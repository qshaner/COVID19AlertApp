package com.example.covid19notification.ui.symptomTracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.covid19notification.R
import kotlinx.android.synthetic.main.activity_symptom_details.*
import java.util.ArrayList

private lateinit var btnSubmitSymptoms: Button
private lateinit var btnDeleteEntry: Button

class SymptomDetails : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptom_details)

        btnSubmitSymptoms = findViewById(R.id.btnSubmitSymptom)
        btnSubmitSymptoms.setOnClickListener(this)
        btnDeleteEntry = findViewById(R.id.btnDeleteSymptom)
        btnDeleteEntry.setOnClickListener(this)

        getIncomingIntent();
    }

    override fun onClick(v:View){
        when(v.id){
            R.id.btnDeleteSymptom -> deleteEntry()
            R.id.btnSubmitSymptom -> submitEntry()
        }
    }

    //TODO: Delete from DB
    fun deleteEntry(){
      //  val activity = requireActivity()
        Toast.makeText(this.applicationContext, "onClick: Delete Entry Pressed", Toast.LENGTH_SHORT).show()
    }

    //TODO: Add to DB
    fun submitEntry(){
        Toast.makeText(this.applicationContext, "onClick: Submit Entry Pressed", Toast.LENGTH_SHORT).show()
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