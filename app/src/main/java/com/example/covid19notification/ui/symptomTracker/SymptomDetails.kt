package com.example.covid19notification.ui.symptomTracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.covid19notification.Database.Symptoms
import com.example.covid19notification.Model.Symptom
import com.example.covid19notification.Model.User
import com.example.covid19notification.R
import kotlinx.android.synthetic.main.activity_symptom_details.*
import kotlin.collections.ArrayList

private lateinit var btnSubmitSymptoms: Button
private lateinit var btnDeleteEntry: Button
private lateinit var userid:String;
class SymptomDetails : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptom_details)

        btnSubmitSymptoms = findViewById(R.id.btnSubmitSymptom)
        btnSubmitSymptoms.setOnClickListener(this)
        btnDeleteEntry = findViewById(R.id.btnDeleteSymptom)
        btnDeleteEntry.setOnClickListener(this)

        userid = ""
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
        val symptom = Symptom(symptomDate.text.toString(), (etSymptomList.text.split(",") as ArrayList<String>))
        Symptoms.delete(symptom)
    }

    fun submitEntry(){
    //    userid = (intent.getStringExtra("user") as User).id
        Toast.makeText(this.applicationContext, "onClick: Submit Entry Pressed", Toast.LENGTH_SHORT).show()

        var symptomsEntry = etSymptomList.text.split(",") as ArrayList<String>
        var dateEntry = symptomDate.text.toString();

        Symptoms.add(Symptom(dateEntry, symptomsEntry))
    }

    private fun getIncomingIntent(){
        if(intent.hasExtra("date") && intent.hasExtra("symptoms")){
            var date = intent.getStringExtra("date")
            var symptomEntries: ArrayList<String?> = intent.getStringArrayListExtra("symptoms") as ArrayList<String?>

            if(intent.hasExtra("user")){
                userid = (intent.getStringExtra("user") as User).id
            }
            setEntry(date, symptomEntries)
        }
    }

    private fun setEntry(entryDate:String?, entrySymptoms: ArrayList<String?>){
        var date: EditText = findViewById(R.id.symptomDate)
        date.setText(entryDate);
        var symptoms: EditText = findViewById(R.id.etSymptomList)
        symptoms.setText(entrySymptoms.toString())
    }


}