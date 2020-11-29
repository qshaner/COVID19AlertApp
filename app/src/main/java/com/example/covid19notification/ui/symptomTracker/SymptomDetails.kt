package com.example.covid19notification.ui.symptomTracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.covid19notification.Database.Symptoms
import com.example.covid19notification.Model.Symptom
import com.example.covid19notification.Model.User
import com.example.covid19notification.R
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

    fun deleteEntry(){
      //  val activity = requireActivity()
        Toast.makeText(this.applicationContext, "onClick: Delete Entry Pressed", Toast.LENGTH_SHORT).show()

        if(intent.hasExtra("user")&& intent.hasExtra("symptoms") && intent.hasExtra("date")){
            var intentSymptom = Symptom(intent.getStringExtra("date").toString(), intent.getStringArrayListExtra("symptoms") as ArrayList<String>)
            var intentUser: User = (intent.getSerializableExtra("user") as User)
            Symptoms.delete(intentUser.id , intentSymptom)
        }
    }

    fun submitEntry(){
        Toast.makeText(this.applicationContext, "onClick: Submit Entry Pressed", Toast.LENGTH_SHORT).show()
        //get data
        var reg: Regex = Regex("(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d")
        var date: EditText = findViewById(R.id.symptomDate)
        var symptoms: EditText = findViewById(R.id.etSymptomList)

Log.d("Entries1: ", "Do we have any IntentExtras? $intent.get")

        if(intent.hasExtra("user")) {
            Log.d("extras0: ", "Have User Info ")
            var intentUser: User = (intent.getSerializableExtra("user") as User)
            if (intent.hasExtra("symptoms") && intent.hasExtra("date")) {
                Log.d("extras1: ", " we have intents" )
                var intentSymptom = Symptom(
                    intent.getStringExtra("date").toString(),
                    intent.getStringArrayListExtra("symptoms") as ArrayList<String>
                )
                Log.d("extras1: ", intentSymptom.date)
                Symptoms.update(intentUser.id, intentSymptom)
                //there was a date entry before, so update
            } else if (date.text.matches(reg)) {
                //no entry before
                Log.d("extras2: ", "There were no entries before")
                var symptomsAsArrayList = symptoms.text.toString().split(", ")
                if(Symptoms.get(intentUser.id, date.text.toString()).isSuccessful){
                    //Date was already in the system, update the symptoms
                    Symptoms.update(intentUser.id, Symptom(date.text.toString(), symptomsAsArrayList as ArrayList<String>))
                }
                else {
                    //date was not in system, add a new entry
                    Symptoms.add(intentUser.id, Symptom(date.text.toString(), symptomsAsArrayList as ArrayList<String>))
                    Log.d("extras3: ", " Date was not in system, add new entry")
                }
            } else {
                Toast.makeText(this.applicationContext, "Dates must be in MM/DD/YYYY format!", Toast.LENGTH_LONG).show()
                Log.d("extras4", "Date was in the wrong format")
                //Date must be in MM/DD/YYYY format
            }
        }
        //they shouldn't be able to get here at all if they aren't logged in
    }

    private fun getIncomingIntent(){
        if(intent.hasExtra("date") && intent.hasExtra("symptoms")&&intent.hasExtra("user")){
            var user = intent.getStringArrayExtra("user")
            var date = intent.getStringExtra("date")
            var symptomEntries: ArrayList<String> = intent.getStringArrayListExtra("symptoms") as ArrayList<String>

            setEntry(date, symptomEntries)
        }
    }

    private fun setEntry(entryDate:String?, entrySymptoms: ArrayList<String>){
        var date: EditText = findViewById(R.id.symptomDate)
        date.setText(entryDate);
        var symptoms: EditText = findViewById(R.id.etSymptomList)
        symptoms.setText(entrySymptoms.toString())
    }


}