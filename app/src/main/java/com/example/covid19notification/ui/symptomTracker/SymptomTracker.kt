package com.example.covid19notification.ui.symptomTracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid19notification.Model.Symptom
import com.example.covid19notification.R
import kotlinx.android.synthetic.main.activity_symptom_tracker.*

class SymptomTracker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptom_tracker)

        val symptomList = generateDummyList(500)
        recycler_view.adapter = SymptomAdapter(symptomList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true);
        //performance optimization  ^^^^
    }

    //TODO: Change this to a 'get things from the DB using the UID'
    private fun generateDummyList(size: Int):List<Symptom>{
        val list = ArrayList<Symptom>()

        for(i in 0 until size) {
            val item = Symptom("$i", "DD/MM/YYYY", "the list of all of the things")
            list +=item
        }
        return list
    }

}