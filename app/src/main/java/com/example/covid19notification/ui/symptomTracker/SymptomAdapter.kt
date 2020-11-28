package com.example.covid19notification.ui.symptomTracker

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19notification.Model.Symptom
import com.example.covid19notification.R


class SymptomAdapter(
    context: Context,
    symptomEntries: ArrayList<Symptom>
) :
    RecyclerView.Adapter<SymptomAdapter.ViewHolder>() {
    private var mSymptomEntries: ArrayList<Symptom> = ArrayList()
    private val mContext: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.example_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val tag = "OnBindViewHolder"
Log.d(tag," onBindViewHolder() called")
        holder.symptomDate.text = mSymptomEntries[position].date
        holder.parentLayout.setOnClickListener {
            Log.d(
                tag,
                "onClick: clicked on: " + mSymptomEntries[position]
            )
            Toast.makeText(mContext, mSymptomEntries[position].date, Toast.LENGTH_SHORT).show()
            val intent = Intent(mContext, SymptomDetails::class.java)
            intent.putExtra("symptoms", mSymptomEntries[position].symptoms)
            intent.putExtra("date", mSymptomEntries[position].date)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mSymptomEntries.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var symptomDate: TextView
        var symptomList: TextView
        var parentLayout: RelativeLayout

        init {
            symptomDate = itemView.findViewById(R.id.item_date)
            symptomList = itemView.findViewById(R.id.item_content)
            parentLayout = itemView.findViewById(R.id.example_item)
        }
    }

    companion object {
        private const val TAG = "SymptomTrackerAdapter"
    }

    init {
        mSymptomEntries = symptomEntries
        mContext = context
    }
}
