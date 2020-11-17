package com.example.covid19notification.ui.symptomTracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid19notification.Model.Symptom
import com.example.covid19notification.R
import kotlinx.android.synthetic.main.activity_symptom_tracker.*
import kotlin.random.Random

//YT tutorial on recyclerview that I used for this: https://www.youtube.com/watch?v=6Gm3eMG8KqI  (parts 1-4, about an hour in length)

class symptom_tracker : AppCompatActivity(), SymptomAdapter.OnItemClickListener {
  private val symptomList = generateDummyList(500)
    private val adapter = SymptomAdapter(symptomList, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptom_tracker)

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true);
        //performance optimization  ^^^^
    }

    fun insertItem(view: View) {
        val index = Random.nextInt(8)

        val newItem = Symptom(
            "2", "New item at position", "2/4/2020"
        )
        symptomList.add(index, newItem)
        adapter.notifyItemInserted(index)
    }

    fun removeItem(view: View) {

    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "item $position clicked, item: ${symptomList[position]}", Toast.LENGTH_LONG).show()
        val clickedItem = symptomList[position]



    }

    //TODO: Change this to a 'get things from the DB using the UID'
    private fun generateDummyList(size: Int):ArrayList<Symptom>{
        val list = ArrayList<Symptom>()

        for(i in 0 until size) {
            val item = Symptom("$i", "DD/MM/YYYY", "the list of all of the things")
            list +=item
        }
        return list
    }

}