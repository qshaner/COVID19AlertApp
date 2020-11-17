package com.example.covid19notification.ui.symptomTracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19notification.Model.Symptom
import com.example.covid19notification.R
import kotlinx.android.synthetic.main.example_item.view.*

class SymptomAdapter(private val symptomList: List<Symptom>
                     , private val listener: OnItemClickListener
) : RecyclerView.Adapter<SymptomAdapter.SymptomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymptomViewHolder {
        //called by the recyclerview when a new card is made
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.example_item,
            parent, false)

        return SymptomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SymptomViewHolder, position: Int) {
        //takes view holder and then puts data of current item into it
        val currentItem = symptomList[position]

        holder.dateView.text = currentItem.ImageResource
        holder.contentView.text = currentItem.text1
    }

    override fun getItemCount(): Int {
        return symptomList.size
    }

  inner class SymptomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{

        //view holder is a single row/card of the recyclerview
        val dateView: TextView = itemView.item_date
        val contentView: TextView = itemView.item_content
        val btnEdit: ImageButton = itemView.item_button_edit

        val btnDlt: ImageButton = itemView.item_button_delete
        //now we have references to individual parts of the card

        init {
            itemView.setOnClickListener(this)
            btnEdit.setOnClickListener(this)
            btnDlt.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            //forward the click to the screen that displays the recyclerView
            val position: Int = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }

        }
        //viewholder has references to the parts now

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}