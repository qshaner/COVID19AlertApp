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
import java.util.ArrayList

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

    //TODO: Delete from  (Get symptom date from text field, then delete it
    fun deleteEntry(){
      //  val activity = requireActivity()
        Toast.makeText(this.applicationContext, "onClick: Delete Entry Pressed", Toast.LENGTH_SHORT).show()
       // Symptoms.delete(symptom)
    }

    //TODO: make generic
    fun submitEntry(){
    //    userid = (intent.getStringExtra("user") as User).id
        Toast.makeText(this.applicationContext, "onClick: Submit Entry Pressed", Toast.LENGTH_SHORT).show()
        var testArr: ArrayList<String> = arrayListOf("cough", "sneezing", "chills")
        Symptoms.add(Symptom("02-08-2023", testArr))

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